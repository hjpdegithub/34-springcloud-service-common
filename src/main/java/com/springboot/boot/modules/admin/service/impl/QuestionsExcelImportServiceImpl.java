package com.springboot.boot.modules.admin.service.impl;


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.springboot.boot.common.ExcelListener;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.entity.MpExamination;
import com.springboot.boot.modules.admin.entity.MpOption;
import com.springboot.boot.modules.admin.entity.MpQuestionBank;
import com.springboot.boot.modules.admin.entity.MpQuestionBankExample;
import com.springboot.boot.modules.admin.mapper.MpExaminationMapper;
import com.springboot.boot.modules.admin.mapper.MpQuestionBankMapper;
import com.springboot.boot.modules.admin.service.QuestionsExcelService;
import com.springboot.boot.modules.admin.vo.test.MpQuestionBankVo;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xf
 * @version 1.0.0
 * @ClassName ExcelServiceImpl
 * @Description TODO
 * @createTime 2020.09.06 13:50
 */
@Service
public class QuestionsExcelImportServiceImpl implements QuestionsExcelService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionsExcelImportServiceImpl.class);
    @Resource
    private MpQuestionBankMapper mpQuestionBankMapper;
    @Resource
    private com.springboot.boot.modules.admin.mapper.MpOptionMapper MpOptionMapper;
    @Resource
    private MpExaminationMapper mpExaminationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String questionsExcelImport(MultipartFile file, Long pid) throws IOException {
        //先通过试卷ID查出试卷信息
        MpExamination mpExamination = mpExaminationMapper.selectByPrimaryKey(pid);
        Integer singleChoiceNum = mpExamination.getSingleChoiceNum();
        Integer multipleChoiceNum = mpExamination.getMultipleChoiceNum();
        Integer judgeNum = mpExamination.getJudgeNum();
        int singleChoiceNumInt = singleChoiceNum == null ? 0 : singleChoiceNum.intValue();
        int multipleChoiceNumInt = multipleChoiceNum == null ? 0 : multipleChoiceNum.intValue();
        int judgeNumInt = judgeNum == null ? 0 : judgeNum.intValue();
        int singleChoiceNumTmp = 0;
        int multipleChoiceNumTmp = 0;
        int judgeNumTmp = 0;
        MpQuestionBankExample mpQuestionBankExample = new MpQuestionBankExample();
        mpQuestionBankExample.
                createCriteria().
                andExaminationIdEqualTo(pid).
                andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpQuestionBank> mpQuestionBankList =
                mpQuestionBankMapper.selectByExample(mpQuestionBankExample);

        //既存数量
        for (
              MpQuestionBank oldQuestionBank : mpQuestionBankList ) {
            if (oldQuestionBank.getType() != null && oldQuestionBank.getType().toString().equals("1")){
                singleChoiceNumTmp++;
            }
            if (oldQuestionBank.getType() != null && oldQuestionBank.getType().toString().equals("2")) {
                multipleChoiceNumTmp++;
            }
            if (oldQuestionBank.getType() != null && oldQuestionBank.getType().toString().equals("3")) {
                judgeNumTmp++;
            }
        }


        //
        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //传入参数
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, MpQuestionBankVo.class));
        //获取数据
        List<Object> list = listener.getDatas();
        //校验数量
        for (Object o : list) {
            MpQuestionBankVo mpQuestionBankVo = (MpQuestionBankVo) o;
            if (mpQuestionBankVo.getType() != null && mpQuestionBankVo.getType().toString().equals("1")) {
                singleChoiceNumTmp++;
            }
            if (mpQuestionBankVo.getType() != null && mpQuestionBankVo.getType().toString().equals("2")) {
                multipleChoiceNumTmp++;
            }
            if (mpQuestionBankVo.getType() != null && mpQuestionBankVo.getType().toString().equals("3")) {
                judgeNumTmp++;
            }
        }
        if (singleChoiceNumInt != singleChoiceNumTmp) {
            return "单选题数量已经达到上线";
        }
        if (multipleChoiceNumInt != multipleChoiceNumTmp) {
            return "多选题数量已经达到上线";
        }

        if (judgeNumInt != judgeNumTmp) {
            return "判断题数量已经达到上线";
        }
        List<MpQuestionBankVo> originalList = new ArrayList<MpQuestionBankVo>();

        MpQuestionBankVo catagory = new MpQuestionBankVo();
        //转换数据类型
        for (int i = 0; i < list.size(); i++) {
            catagory = (MpQuestionBankVo) list.get(i);
            originalList.add(catagory);
        }
        Long id = SnowFlakeUtils.getFlowIdInstance().nextId();
        //对list进行去重并拿到新的list
        //执行批量插入
        int i = 0;
        try {

            if (originalList.size() > 0) {
                logger.info("执行批量入库");
                for (MpQuestionBankVo e : originalList) {
                    i++;
                    if ("".equals(e.getType()) || e.getType() == null ||
                            "".equals(e.getRightAnswer()) || e.getRightAnswer() == null ||
                            "".equals(e.getChoice()) || e.getChoice() == null) {
                        throw new RuntimeException();
                    }
                    MpQuestionBank mpQuestionBank = new MpQuestionBank();
                    BeanCopy.copy(e, mpQuestionBank);
                    mpQuestionBank.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
                    //试卷ID
                    mpQuestionBank.setExaminationId(pid);
                    mpQuestionBank.setDeleFlag(CommonEnum.USED.getCode());
                    mpQuestionBank.setCreateTime(new Date());
                    mpQuestionBankMapper.insert(mpQuestionBank);
                    String choice = e.getChoice();
                    String[] choices = choice.split("##");
                    if (e.getType().toString().equals("1") || e.getType().toString().equals("2")
                    ) {
                        if (choices.length > 6) {
                            throw new RuntimeException();
                        }
                    }
                    if (e.getType().toString().equals("3")) {
                        if (choices.length > 2) {
                            throw new RuntimeException();
                        }
                        if (e.getRightAnswer().contains(",")) {
                            throw new RuntimeException();
                        }
                    }

                    if (e.getType().toString().equals("3")) {
                        if (e.getRightAnswer().contains(",")) {
                            throw new RuntimeException();
                        }
                    }
                    if (e.getType().toString().equals("1")) {
                        if (e.getRightAnswer().length() > 1 || e.getRightAnswer().contains(",")) {
                            throw new RuntimeException();
                        }
                    }
                    List<String> choiceList = Arrays.asList(choices);
                    for (String choiceOption : choiceList) {
                        String[] choiceArray = choiceOption.split(":");
                        if (choiceArray.length != 2) {
                            throw new RuntimeException();
                        }
                        MpOption mpOption = new MpOption();
                        mpOption.setOpt(choiceArray[0]);
                        mpOption.setOptionName(choiceArray[1]);
                        mpOption.setQuestionId(mpQuestionBank.getId());
                        mpOption.setDeleFlag(CommonEnum.USED.getCode());
                        mpOption.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
                        mpOption.setCreateTime(new Date());
                        MpOptionMapper.insert(mpOption);
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            return "(" + String.valueOf(i) + ")";
        }
        logger.info("解析数据为空");
        return "sucess";
    }


}
