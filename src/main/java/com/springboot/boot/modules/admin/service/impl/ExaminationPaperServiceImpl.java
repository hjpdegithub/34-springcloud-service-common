package com.springboot.boot.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.common.enums.CommonEnum;
import com.springboot.boot.common.enums.TypeEnum;
import com.springboot.boot.common.exc.BusinessException;
import com.springboot.boot.modules.admin.dto.QuestionBankAddAndUpdateDto;
import com.springboot.boot.modules.admin.dto.QuestionBankPracticeSBDto;
import com.springboot.boot.modules.admin.dto.examination.*;
import com.springboot.boot.modules.admin.dto.sign.ChilckSignUpDto;
import com.springboot.boot.modules.admin.entity.*;
import com.springboot.boot.modules.admin.mapper.*;
import com.springboot.boot.modules.admin.service.*;
import com.springboot.boot.modules.admin.vo.ChengjiVo;
import com.springboot.boot.modules.admin.vo.examination.*;
import com.springboot.boot.modules.admin.vo.question.AppOptionVo;
import com.springboot.boot.modules.admin.vo.question.AppQuestionVo;
import com.springboot.boot.modules.admin.vo.question.AppTypesVo;
import com.springboot.boot.modules.admin.vo.question.QuestionSearchVo;
import com.springboot.boot.utils.ApiCode;
import com.springboot.boot.utils.ApiResult;
import com.springboot.boot.utils.BeanCopy;
import com.springboot.boot.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @ClassName ExaminationPaperServiceImpl
 * @Description TODO 试卷管理
 * @Author jhzhou
 * @Date 2022/4/7 0007 14:43
 * @Version 2.0
 **/
@Service
@Slf4j
public class ExaminationPaperServiceImpl implements ExaminationPaperService {


    @Resource
    private MpExaminationMapper examinationMapper;

    @Resource
    private MpExaminationRuleMapper examinationRuleMapper;

    @Resource
    private MpErrorQuestionMapper mpErrorQuestionMapper;

    @Resource
    private MpExaminationBusinessMapper examinationBusinessMapper;

    @Resource
    private ExaminationRuleService service;
    @Resource
    private QuestionService questionService;
    @Resource
    private SignUpService signUpService;
    @Resource
    private UserExamService userExamService;

    @Resource
    private OptionService optionService;
    @Resource
    private MpUserExamMapper userExamMapper;
    @Resource
    private MpExamAchievementMapper achievementMapper;
    @Resource
    private MpQuestionBankMapper questionBankMapper;

    /**
     * 试卷的新增以及修改
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult addOrUpdate(ExaminationAddOrUpdateDto dto) {
        MpExamination examination = new MpExamination();
        BeanCopy.copy(dto, examination);
        //公共参数============================
        examination.setUpdateUser(dto.getUserId());
        examination.setUpdateTime(new Date());
        examination.setDeleFlag(CommonEnum.USED.getCode());
        examination.setUpType(CommonEnum.NO_UP.getCode());
        //判断是否为新增
        if (null != dto.getId()) {
            //编辑
            examination.setId(dto.getId());
            int i = examinationMapper.updateByPrimaryKeySelective(examination);
            if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
                throw new BusinessException("试卷编辑错误！");
            }
            if (null != dto.getJudgeRule()) {

                //试卷类的判断
                if (examination.getExaminationType() == 1) {
                    if (null == dto.getSingleRule().getId()
                            || null == dto.getMultipleRule().getId()
                            || null == dto.getJudgeRule().getId()) {
                        throw new BusinessException("单选或多选或判断，规则id不能为空！");
                    }
                    //单选
                    service.updateExanRule(dto.getSingleRule(), TypeEnum.SINGLE.getCode(), dto, dto.getId(), dto.getSingleRule().getId());
                    //多选
                    service.updateExanRule(dto.getMultipleRule(), TypeEnum.MULTIPLE.getCode(), dto, dto.getId(), dto.getMultipleRule().getId());
                    //判断
                    service.updateExanRule(dto.getJudgeRule(), TypeEnum.JUDGE.getCode(), dto, dto.getId(), dto.getJudgeRule().getId());
                }

                //认证类的判断
                if (examination.getExaminationType() == 3) {
                    if (null == dto.getSingleRule().getId()
                            || null == dto.getMultipleRule().getId()
                            || null == dto.getJudgeRule().getId()) {
                        throw new BusinessException("单选或多选或判断，规则id不能为空！");
                    }
                    //单选
                    service.updateExanRule(dto.getSingleRule(), TypeEnum.SINGLE.getCode(), dto, dto.getId(), dto.getSingleRule().getId());
                    //多选
                    service.updateExanRule(dto.getMultipleRule(), TypeEnum.MULTIPLE.getCode(), dto, dto.getId(), dto.getMultipleRule().getId());
                    //判断
                    service.updateExanRule(dto.getJudgeRule(), TypeEnum.JUDGE.getCode(), dto, dto.getId(), dto.getJudgeRule().getId());
                }

            } else {
                if (null == dto.getSingleRule().getId()
                        || null == dto.getMultipleRule().getId()
                ) {
                    throw new BusinessException("单选或多选，规则id不能为空！");
                }
                //单选
                service.updateExanRule(dto.getSingleRule(), TypeEnum.SINGLE.getCode(), dto, dto.getId(), dto.getSingleRule().getId());
                //多选
                service.updateExanRule(dto.getMultipleRule(), TypeEnum.MULTIPLE.getCode(), dto, dto.getId(), dto.getMultipleRule().getId());
                //判断
                //service.updateExanRule(dto.getJudgeRule(), TypeEnum.JUDGE.getCode(), dto, dto.getId(), dto.getJudgeRule().getId());
            }
        } else {
            long id = SnowFlakeUtils.getFlowIdInstance().nextId();
            examination.setCrateUser(dto.getUserId());
            examination.setCreateTime(new Date());
            examination.setId(id);
            //新增考试表
            int i = examinationMapper.insertSelective(examination);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("新增试卷失败！");
            }
            //考试规则的新增
            //单选
            if (null != dto.getSingleRule()) {
                service.addExamRule(dto.getSingleRule(), TypeEnum.SINGLE.getCode(), dto, id);
            }
            //多选
            if (null != dto.getMultipleRule()) {
                service.addExamRule(dto.getMultipleRule(), TypeEnum.MULTIPLE.getCode(), dto, id);
            }
            //判断
            if (null != dto.getJudgeRule())
                service.addExamRule(dto.getJudgeRule(), TypeEnum.JUDGE.getCode(), dto, id);
        }
        return ApiResult.success();
    }

    /**
     * 试卷查询接口
     *
     * @param dto
     * @return
     */
    @Override
    public PageInfo<ExaminationVo> search(ExaminationSearchDto dto) {

        if (dto.getPaging()) {
            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        }
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        if (null != dto.getName() && !dto.getName().equals("")) {
            criteria.andNameLike("%" + dto.getName() + "%");
        }
        if (null != dto.getDepartmentId()) {
            criteria.andDepartmentIdEqualTo(dto.getDepartmentId());
        }
        if (null != dto.getExaminationType()) {
            criteria.andExaminationTypeEqualTo(dto.getExaminationType());
        }
        if (null != dto.getRangeType()) {
            criteria.andRangeTypeEqualTo(dto.getRangeType());
        }
        if (null != dto.getUnitId()) {
            criteria.andUnitIdEqualTo(dto.getUnitId());
        }
        List<ExaminationVo> mpExaminations = examinationBusinessMapper.selectByDto(dto);

        PageInfo<ExaminationVo> pageInfo = new PageInfo<>(mpExaminations);
        return pageInfo;
    }


    @Override
    public List<ExaminationVo> searchNoPage(ExaminationSearchNoPageDto dto) {


        List<ExaminationVo> mpExaminations = examinationBusinessMapper.searchNoPage(dto);

        return mpExaminations;


    }

    /**
     * 试卷删除接口
     *
     * @param dto
     * @return
     */
    @Override
    public ApiResult delete(ExaminationDeleteDto dto) {
        MpQuestionBankExample questionBankExample = new MpQuestionBankExample();

        Long id = dto.getId();
        List<Long> ids = dto.getIds();
        if (id != null) {
            MpExamination examination = new MpExamination();
            examination.setId(id);
            examination.setDeleFlag(CommonEnum.DELETE.getCode());
            int i = examinationMapper.updateByPrimaryKeySelective(examination);
            if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                ApiResult.error(ApiCode.FAIL, "试卷删除失败");
            }
            //通过试卷删除题库相关信息
            MpQuestionBankExample.Criteria criteria = questionBankExample.createCriteria();
            criteria.andExaminationIdEqualTo(dto.getId());
            List<MpQuestionBank> questionBanks = questionBankMapper.selectByExample(questionBankExample);
            questionBanks.forEach(e -> {
                MpQuestionBank questionBank = new MpQuestionBank();
                questionBank.setId(e.getId());
                questionBank.setDeleFlag(CommonEnum.DELETE.getCode());
                questionBankMapper.updateByPrimaryKeySelective(questionBank);
                optionService.deleteOptionBYQuestion(e.getId());
            });
//            service.deleteByExamintionId(dto.getId());
            return ApiResult.success();


        } else {
            for (Long ide : ids) {
                MpExamination examination = new MpExamination();
                examination.setId(ide);
                examination.setDeleFlag(CommonEnum.DELETE.getCode());
                int i = examinationMapper.updateByPrimaryKeySelective(examination);
                if (i <= CommonEnum.DELETE_ERROR.getCode()) {
                    ApiResult.error(ApiCode.FAIL, "试卷删除失败");
                }

//                service.deleteByExamintionId(dto.getId());
            }
            //通过试卷删除题库相关信息
            MpQuestionBankExample.Criteria criteria = questionBankExample.createCriteria();
            criteria.andExaminationIdIn(ids);
            List<MpQuestionBank> questionBanks = questionBankMapper.selectByExample(questionBankExample);
            questionBanks.forEach(e -> {
                MpQuestionBank questionBank = new MpQuestionBank();
                questionBank.setId(e.getId());
                questionBank.setDeleFlag(CommonEnum.DELETE.getCode());
                questionBankMapper.updateByPrimaryKeySelective(questionBank);
                optionService.deleteOptionBYQuestion(e.getId());
            });
            return ApiResult.success();
        }


    }

    @Override
    public MpExamination selectExamById(Long id) {
        //通过id查询出试卷信息数据
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        return mpExaminations.get(0);

    }

    /**
     * 试卷回显接口
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult selectById(Long id) {
        //通过id查询出试卷信息数据
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        //只会回去一条数据
        if (mpExaminations.size() <= 0) {
            throw new BusinessException("试卷信息为空！");
        }
        ExaminationAndRuleVo examinationAndRuleVo = new ExaminationAndRuleVo();
        MpExamination examination = mpExaminations.get(0);
        BeanCopy.copy(examination, examinationAndRuleVo);
        //查询试卷规则数据输出
        List<MpExaminationRule> mpExaminationRules = service.selectById(id);
        List<MpExaminationRule> objects = new ArrayList<>();

        mpExaminationRules.forEach(e -> {
            MpExaminationRule rule = new MpExaminationRule();
            BeanCopy.copy(e, rule);
            objects.add(rule);
        });
        examinationAndRuleVo.setRules(objects);
        return ApiResult.success(examinationAndRuleVo);
    }

    /**
     * 试卷检查
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult checkQuestionByExamId(QuestionBankAddAndUpdateDto dto) {
        //通过试卷id查询试卷信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andIdEqualTo(dto.getExaminationId());
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        if (mpExaminations.isEmpty()) {
            throw new BusinessException("试卷为空");
        }
        MpExamination examination = mpExaminations.get(0);
        //通过试卷id和类型查询题库集合
        List<MpQuestionBank> mpOptions = questionService.selectByExIdAndType(dto);
        if (null == dto.getId()) {
            switch (dto.getType()) {
                //单选
                case 1:
                    //开始判断
                    if (mpOptions.size() >= examination.getSingleChoiceNum().intValue()) {
                        return ApiResult.error(500, "单选数量上限无法添加");
                    }
                    break;
                //多选
                case 2:
                    if (mpOptions.size() >= examination.getMultipleChoiceNum().intValue()) {
                        return ApiResult.error(500, "多选数量上限无法添加");
                    }
                    break;
                //判断
                default:
                    if (mpOptions.size() >= examination.getJudgeNum().intValue()) {
                        return ApiResult.error(500, "判断数量上限无法添加");
                    }
            }
        }
        return ApiResult.success();
    }

    public List<MpExamination> whereSelectExam(Long id) throws BusinessException {
        //通过id查询试卷信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andIdEqualTo(id);
        criteria.andExaminationTypeEqualTo(CommonEnum.EXAM.getCode());
        criteria.andRangeTypeEqualTo(CommonEnum.DATA_EXAM.getCode());
        criteria.andUpTypeEqualTo(CommonEnum.UP.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        if (mpExaminations.isEmpty()) {
            throw new BusinessException("试卷信息为空！");
        }
        return mpExaminations;
    }
    public List<MpExamination> whereSelectExamByAuth(Long id) throws BusinessException {
        //通过id查询试卷信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andIdEqualTo(id);
        criteria.andExaminationTypeEqualTo(CommonEnum.AUTH_EXAM.getCode());
        criteria.andRangeTypeEqualTo(CommonEnum.DATA_EXAM.getCode());
        criteria.andUpTypeEqualTo(CommonEnum.UP.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        if (mpExaminations.isEmpty()) {
            throw new BusinessException("试卷信息为空！");
        }
        return mpExaminations;
    }


    public List<MpExamination> whereSelectExam2(Long id) throws BusinessException {
        //通过id查询试卷信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andIdEqualTo(id);
        criteria.andExaminationTypeEqualTo(CommonEnum.WENJUAN.getCode());
        criteria.andRangeTypeEqualTo(CommonEnum.DATA_EXAM.getCode());
        criteria.andUpTypeEqualTo(CommonEnum.UP.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        if (mpExaminations.isEmpty()) {
            throw new BusinessException("试卷信息为空！");
        }
        return mpExaminations;
    }


    /**
     * app试卷详情
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult detail(Long id, Long userId) {
        AppExaminationDetailVo detailVo = new AppExaminationDetailVo();
        List<MpExamination> mpExaminations = whereSelectExam(id);
        MpExamination examination = mpExaminations.get(0);
        log.info("app试卷详情报文输出=====" + JSONObject.toJSONString(examination));
        BeanCopy.copy(examination, detailVo);
        //是否报名
        List<MpSignUp> mpSignUp = signUpService.selectByIdAndUserId(id, userId);
        if (mpSignUp.isEmpty()) {
            detailVo.setBType(CommonEnum.NO_SING_UP.getCode());
        } else {
            detailVo.setBType(CommonEnum.SIGN_UP.getCode());
        }
        //查看报名人数
        List<MpSignUp> mpSignUps = signUpService.selectByIdAndUserId(id, null);
        detailVo.setBNum(mpSignUps.size());
        //查看考试次数
        int count = userExamService.selectExamByCount(id, userId);
        detailVo.setKaoNum(count);
        return ApiResult.success(detailVo);
    }

    /**
     * 点击报名接口
     *
     * @param dto
     * @return
     */
    @Override
    public ApiResult calickSignUp(ChilckSignUpDto dto) {
        //查询该人是否报名
        List<MpSignUp> mpSignUps = signUpService.selectByIdAndUserId(dto.getExamId(), dto.getUserId());

        MpSignUp signUp = new MpSignUp();
        signUp.setExamId(dto.getExamId());
        signUp.setUpdateTime(new Date());
        signUp.setUpdateUser(dto.getUserId());
        signUp.setUpTime(dto.getStartTime());
        signUp.setUserId(dto.getUserId());
        //判断报名还是取消报名
        if (dto.getSignType() == CommonEnum.CANCEL_SIGN_UP.getCode()) {
            signUp.setDeleFlag(CommonEnum.DELETE.getCode());
            signUp.setId(mpSignUps.get(0).getId());
            signUpService.delete(signUp);
        } else {
            //加入判断
            if (!mpSignUps.isEmpty()) {
                throw new BusinessException("不可重复参加报名");
            }
            signUp.setCrateUser(dto.getUserId());
            signUp.setCreateTime(new Date());
            signUp.setDeleFlag(CommonEnum.USED.getCode());
            signUp.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
            signUp.setSignTime(new Date());
            signUpService.insert(signUp);
        }

        return ApiResult.success();
    }

    /**
     * 题库练习
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult questionBankPractice(Long id) {
        //题库练习报文
        AppQuestionBankPracticeVo bankPracticeVo = new AppQuestionBankPracticeVo();
        //1.查询试卷信息 ，通过考试类型和展示范围查询
        List<MpExamination> mpExaminations = whereSelectExam(id);
        //2.set试卷到报文里
        bankPracticeVo.setId(mpExaminations.get(0).getId());
        bankPracticeVo.setName(mpExaminations.get(0).getName());
        //3.获取该试卷下所有的题库
        QuestionBankAddAndUpdateDto dto = new QuestionBankAddAndUpdateDto();
        dto.setExaminationId(mpExaminations.get(0).getId());
        List<MpQuestionBank> mpQuestionBanks = questionService.selectByExIdAndType(dto);
        log.info("试卷下题库================{}", JSONObject.toJSON(mpQuestionBanks));
        //4.通过type进行分组
        //分组
        Map<Integer, List<MpQuestionBank>> groupBySex = mpQuestionBanks.stream().collect(Collectors.groupingBy(MpQuestionBank::getType));
        //遍历分组
        List<AppTypesVo> appTypesVos = new ArrayList<>();
        for (Map.Entry<Integer, List<MpQuestionBank>> entryUser : groupBySex.entrySet()) {
            AppTypesVo appTypesVo = new AppTypesVo();
            Integer type = entryUser.getKey();//type值
            appTypesVo.setType(type);
            //开始放=========================================题库
            List<MpQuestionBank> entryUserList = entryUser.getValue();
            List<AppQuestionVo> appQuestionVos = new ArrayList<>();
            entryUserList.forEach(e -> {
                AppQuestionVo vo = new AppQuestionVo();
                vo.setExaminationId(e.getExaminationId());
                vo.setId(e.getId());
                vo.setName(e.getName());
                List<String> strings = Arrays.asList(e.getRightAnswer().toUpperCase().split(","));
                Collections.sort(strings);
                vo.setRightAnswer(strings);
                //找选项===============================开始
                List<AppOptionVo> appOptionVos = new ArrayList<>();
                List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                mpOptions.forEach(i -> {
                    AppOptionVo optionVo = new AppOptionVo();
                    optionVo.setId(i.getId());
                    optionVo.setOpt(i.getOpt().toUpperCase());
                    optionVo.setOptionName(i.getOptionName());
                    optionVo.setQuestionId(i.getQuestionId());
                    appOptionVos.add(optionVo);
                });
                //排序
                appOptionVos.sort(Comparator.comparing(AppOptionVo::getOpt));
                vo.setAppOptionVos(appOptionVos);
                appQuestionVos.add(vo);
            });
            appTypesVo.setAppQuestionVos(appQuestionVos);
            appTypesVos.add(appTypesVo);
        }
        //排序
        appTypesVos.sort(Comparator.comparing(AppTypesVo::getType));
        bankPracticeVo.setAppTypesVos(appTypesVos);
        return ApiResult.success(bankPracticeVo);
    }


    /**
     * 问卷查看
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult questionnaireQuery(Long id) {
        //题库练习报文
        AppQuestionBankPracticeVo bankPracticeVo = new AppQuestionBankPracticeVo();
        //1.查询试卷信息 ，通过考试类型和展示范围查询
        List<MpExamination> mpExaminations = whereSelectExam2(id);
        //2.set试卷到报文里
        bankPracticeVo.setId(mpExaminations.get(0).getId());
        bankPracticeVo.setName(mpExaminations.get(0).getName());
        //3.获取该试卷下所有的题库
        QuestionBankAddAndUpdateDto dto = new QuestionBankAddAndUpdateDto();
        dto.setExaminationId(mpExaminations.get(0).getId());
        List<MpQuestionBank> mpQuestionBanks = questionService.selectByExIdAndType(dto);
        log.info("试卷下题库================{}", JSONObject.toJSON(mpQuestionBanks));
        //4.通过type进行分组
        //分组
        Map<Integer, List<MpQuestionBank>> groupBySex = mpQuestionBanks.stream().collect(Collectors.groupingBy(MpQuestionBank::getType));
        //遍历分组
        List<AppTypesVo> appTypesVos = new ArrayList<>();
        for (Map.Entry<Integer, List<MpQuestionBank>> entryUser : groupBySex.entrySet()) {
            AppTypesVo appTypesVo = new AppTypesVo();
            Integer type = entryUser.getKey();//type值
            appTypesVo.setType(type);
            //开始放=========================================题库
            List<MpQuestionBank> entryUserList = entryUser.getValue();
            List<AppQuestionVo> appQuestionVos = new ArrayList<>();
            entryUserList.forEach(e -> {
                AppQuestionVo vo = new AppQuestionVo();
                vo.setExaminationId(e.getExaminationId());
                vo.setId(e.getId());
                vo.setName(e.getName());
                List<String> strings = null;
                if (e.getRightAnswer() != null) {
                    strings = Arrays.asList(e.getRightAnswer().toUpperCase().split(","));
                    Collections.sort(strings);
                    vo.setRightAnswer(strings);
                }
                //找选项===============================开始
                List<AppOptionVo> appOptionVos = new ArrayList<>();
                List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                mpOptions.forEach(i -> {
                    AppOptionVo optionVo = new AppOptionVo();
                    optionVo.setId(i.getId());
                    optionVo.setOpt(i.getOpt().toUpperCase());
                    optionVo.setOptionName(i.getOptionName());
                    optionVo.setQuestionId(i.getQuestionId());
                    appOptionVos.add(optionVo);
                });
                //排序
                appOptionVos.sort(Comparator.comparing(AppOptionVo::getOpt));
                vo.setAppOptionVos(appOptionVos);
                appQuestionVos.add(vo);
            });
            appTypesVo.setAppQuestionVos(appQuestionVos);
            appTypesVos.add(appTypesVo);
        }
        //排序
        appTypesVos.sort(Comparator.comparing(AppTypesVo::getType));
        bankPracticeVo.setAppTypesVos(appTypesVos);
        return ApiResult.success(bankPracticeVo);
    }


    public static void main(String[] args) {
//        List<String> list1 = new ArrayList<>();
//        List<String> list1Resulet = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        List<String> list2Resulet = new ArrayList<>();
//        list1.add("a");
//        list1.add("b");
//        list1.add("c");
//        list2.add("C");
//        list2.add("a");
//        list2.add("b");
//        list1.forEach(e->{
//            String s = e.toUpperCase();
//            list1Resulet.add(s);
//        });
//        list2.forEach(e->{
//            String s = e.toUpperCase();
//            list2Resulet.add(s);
//        });
//        boolean result = list1Resulet.containsAll(list2Resulet) && list2Resulet.containsAll(list1Resulet)
//                && list1Resulet.size() == list2Resulet.size();
//
////        boolean isequal = ListUtils.isEqualList(list1,list2);           //如果相等就返回true
//        System.out.println(result);
//        String str = "111";
//        List list=Arrays.asList(str.split(","));
//        System.out.println(JSONObject.toJSON(list));
    }

    /**
     * 练习交卷
     *
     * @param dto
     * @return
     */
    @Override
    public ApiResult questionBankPracticeSubmit(QuestionBankPracticeSBDto dto) {

        //通过id查询题库中的答案
        QuestionSearchVo vo = questionService.searchById(dto.getId());
        AppQuestionBankPracticeSubmitVo practiceSubmitVo = new AppQuestionBankPracticeSubmitVo();
        //数据库的正确选项
        List<String> tures = Arrays.asList(vo.getRightAnswer().toUpperCase().split(","));
        //正确答案
        practiceSubmitVo.setRightAnswer(tures);
        //是否正确
        //通过前端传过来的选项id集合查询出选项
        List<MpOption> mpOptions = optionService.selectByIds(dto.getOptionId());
        //提取所有的选项（用户选的）
        List<String> ops = mpOptions.stream().map(MpOption::getOpt).collect(Collectors.toList());
        //处理大小写问题
        List<String> trueResult = new ArrayList<>();
        List<String> opsResult = new ArrayList<>();
        tures.forEach(e -> {
            String s = e.toUpperCase();
            trueResult.add(s);
        });
        ops.forEach(e -> {
            String s = e.toUpperCase();
            opsResult.add(s);
        });
        boolean result = trueResult.containsAll(opsResult) && opsResult.containsAll(trueResult)
                && trueResult.size() == opsResult.size();
        if (result) {
            practiceSubmitVo.setType(CommonEnum.TRUE.getCode());
        } else {
            practiceSubmitVo.setType(CommonEnum.FALSE.getCode());
        }

        //如果不正确的情况下放到错题库里
        if (practiceSubmitVo.getType() == CommonEnum.FALSE.getCode()) {
            MpErrorQuestion mpErrorQuestion = new MpErrorQuestion();
            mpErrorQuestion.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
            mpErrorQuestion.setCreateTime(new Date());
            mpErrorQuestion.setUpdateTime(new Date());
            mpErrorQuestion.setCreateUser(dto.getUserId());
            mpErrorQuestion.setUpdateUser(dto.getUserId());
            mpErrorQuestion.setDeleFlag(CommonEnum.USED.getCode());
            mpErrorQuestion.setExamId(dto.getExamId());
            StringBuffer stringBuffer = new StringBuffer();
            List<Long> optionId = dto.getOptionId();
            optionId.stream().forEach(s -> {
                stringBuffer.append(s).
                        append(",");
            });
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            mpErrorQuestion.setOptionId(stringBuffer.toString());
            mpErrorQuestion.setQuestionId(dto.getId());
            mpErrorQuestion.setUserId(dto.getUserId());
            int i = mpErrorQuestionMapper.insertSelective(mpErrorQuestion);
            if (i <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("错题新增失败");
            }
        }
        return ApiResult.success(practiceSubmitVo);
    }

    /**
     * 错题查询
     *
     * @param userId
     * @param examId
     * @return
     */
    @Override
    public ApiResult searchErrorQuestion(Long userId, Long examId) {

        //报文
        AppQuestionBankPracticeVo bankPracticeVo = new AppQuestionBankPracticeVo();
        //1.查询试卷信息 ，通过考试类型和展示范围查询
        List<MpExamination> mpExaminations = whereSelectExam(examId);
        if (mpExaminations.isEmpty()) {
            throw new BusinessException("试卷为空");
        }
        //2.set试卷到报文里
        bankPracticeVo.setId(mpExaminations.get(0).getId());
        bankPracticeVo.setName(mpExaminations.get(0).getName());
        //3.获取该试卷下错题题库
        List<MpQuestionBank> mpQuestionBanks = selectErrorQuestion(userId, examId);
        //4.通过type进行分组
        //分组
        Map<Integer, List<MpQuestionBank>> groupBySex = mpQuestionBanks.stream().collect(Collectors.groupingBy(MpQuestionBank::getType));
        //遍历分组
        List<AppTypesVo> appTypesVos = new ArrayList<>();
        for (Map.Entry<Integer, List<MpQuestionBank>> entryUser : groupBySex.entrySet()) {
            AppTypesVo appTypesVo = new AppTypesVo();
            Integer type = entryUser.getKey();//type值
            appTypesVo.setType(type);
            //开始放=========================================题库
            List<MpQuestionBank> entryUserList = entryUser.getValue();
            List<AppQuestionVo> appQuestionVos = new ArrayList<>();
            entryUserList.forEach(e -> {
                AppQuestionVo vo = new AppQuestionVo();
                vo.setExaminationId(e.getExaminationId());
                vo.setId(e.getId());
                vo.setName(e.getName());
                List<String> strings = Arrays.asList(e.getRightAnswer().toUpperCase().split(","));
                Collections.sort(strings);
                vo.setRightAnswer(strings);
                //找选项===============================开始
                List<AppOptionVo> appOptionVos = new ArrayList<>();
                List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                mpOptions.forEach(i -> {
                    AppOptionVo optionVo = new AppOptionVo();
                    optionVo.setId(i.getId());
                    optionVo.setOpt(i.getOpt().toUpperCase());
                    optionVo.setOptionName(i.getOptionName());
                    optionVo.setQuestionId(i.getQuestionId());
                    appOptionVos.add(optionVo);
                });
                vo.setAppOptionVos(appOptionVos);
                appQuestionVos.add(vo);
            });
            appTypesVo.setAppQuestionVos(appQuestionVos);
            appTypesVos.add(appTypesVo);
        }
        bankPracticeVo.setAppTypesVos(appTypesVos);
        return ApiResult.success(bankPracticeVo);
    }

    /**
     * 上下线切换接口
     *
     * @param switchUpDto
     * @return
     */
    @Override
    public ApiResult SwitchUp(ExaminationSwitchUpDto switchUpDto) {
        MpExamination examination = new MpExamination();
        examination.setId(switchUpDto.getId());
        examination.setUpType(switchUpDto.getUpType());
        examination.setUpdateTime(new Date());
        int i = examinationMapper.updateByPrimaryKeySelective(examination);
        if (i <= CommonEnum.UPDATE_ERROR.getCode()) {
            throw new BusinessException("切换失败");
        }
        return ApiResult.success();
    }

    /**
     * 试卷查看
     *
     * @param userId
     * @param id
     * @param achievementId
     * @return
     */
    @Override
    public ApiResult searchExamById(Long userId, Long id, Long achievementId) {
        List<AppQuestionVo> examCount = new ArrayList<>();
        //题库练习报文
        AppQuestionBankExamVo bankPracticeVo = new AppQuestionBankExamVo();
        //1.查询试卷信息 ，通过考试类型和展示范围查询
        List<MpExamination> mpExaminations = whereSelectExam(id);
        //2.set试卷到报文里
        bankPracticeVo.setId(mpExaminations.get(0).getId());
        bankPracticeVo.setName(mpExaminations.get(0).getName());
        //3.获取该试卷下所有的题库
        List<MpUserExam> mpUserExams = selectUserExam(userId, id, achievementId);

        log.info("试卷下题库================{}", JSONObject.toJSON(mpUserExams));
        //4.通过type进行分组
        //分组
        Map<Integer, List<MpUserExam>> groupByType = mpUserExams.stream().collect(Collectors.groupingBy(MpUserExam::getType));
        //遍历分组
        List<AppTypesVo> appTypesVos = new ArrayList<>();
        for (Map.Entry<Integer, List<MpUserExam>> entryUser : groupByType.entrySet()) {
            AppTypesVo appTypesVo = new AppTypesVo();
            List<AppQuestionVo> appQuestionVos = new ArrayList<>();
            Integer type = entryUser.getKey();//type值
            appTypesVo.setType(type);
            //开始放=========================================题库
            List<MpUserExam> value = entryUser.getValue();//试卷下的题库（分组 选项）
            //抽题
            RandomNumVo randomNumVo = datiNum(value, type, mpExaminations.get(0));
            //存放每个题型的规则
            AppTypesVo.Sum sum = new AppTypesVo.Sum();
            if (type == 1) {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            } else if (type == 2) {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            } else {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            }
            //需要随机去除单选多选判断提数
            //抽取的题数
            List<MpQuestionBank> questionBanks = randomNumVo.getMpQuestionBanks();
            questionBanks.forEach(e -> {
                AppQuestionVo vo = new AppQuestionVo();
                vo.setExaminationId(e.getExaminationId());
                vo.setId(e.getId());
                vo.setName(e.getName());
                vo.setRightAnswer(Arrays.asList(e.getRightAnswer().toUpperCase().split(",")));
                value.forEach(i -> {
                    if (e.getId().intValue() == i.getQuestionId().intValue()) {
                        //todo 这里昨天注释掉了等提交试卷后再看
                        vo.setUserOptionId(Arrays.asList(i.getOptionId().split(",")));
                        //找选项===============================开始
                        List<AppOptionVo> appOptionVos = new ArrayList<>();
                        List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                        mpOptions.forEach(f -> {
                            AppOptionVo optionVo = new AppOptionVo();
                            optionVo.setId(f.getId());
                            optionVo.setOpt(f.getOpt().toUpperCase());
                            optionVo.setOptionName(f.getOptionName());
                            optionVo.setQuestionId(f.getQuestionId());
                            appOptionVos.add(optionVo);
                        });
                        //排序
                        appOptionVos.sort(Comparator.comparing(AppOptionVo::getOpt));
                        vo.setAppOptionVos(appOptionVos);
                    }
                });
                appQuestionVos.add(vo);
            });
            //计算题目总数量
            examCount.addAll(appQuestionVos);
            appTypesVo.setAppQuestionVos(appQuestionVos);
            appTypesVo.setCount(sum);
            appTypesVos.add(appTypesVo);
        }
        //试卷分数
        bankPracticeVo.setTotalPoints(mpExaminations.get(0).getPaper());
        //题目数量
        bankPracticeVo.setExamCount(examCount.size());
        appTypesVos.sort(Comparator.comparing(AppTypesVo::getType));
        bankPracticeVo.setAppTypesVos(appTypesVos);
        return ApiResult.success(bankPracticeVo);
    }

    @Override
    public ApiResult select() {
        MpExaminationExample examination = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examination.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andRangeTypeEqualTo(CommonEnum.EXAM.getCode());
        List<MpExamination> mpExaminationList = examinationMapper.selectByExample(examination);
        return ApiResult.success(mpExaminationList);
    }

    @Override
    public List<MpExamination> selectByName(ExaminationAddOrUpdateDto dto) {
        MpExaminationExample examination = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examination.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andRangeTypeEqualTo(CommonEnum.EXAM.getCode());
        criteria.andNameEqualTo(dto.getName());
        if (null != dto.getId() ){
            criteria.andIdNotEqualTo(dto.getId());
        }
        List<MpExamination> mpExaminationList = examinationMapper.selectByExample(examination);
        return mpExaminationList;
    }

    public List<MpUserExam> selectUserExam(Long userId, Long id, Long achievementId) {
        MpUserExamExample examExample = new MpUserExamExample();
        MpUserExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andExamIdEqualTo(id);
        criteria.andUserIdEqualTo(userId);
        criteria.andAchievementIdEqualTo(achievementId);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpUserExam> mpUserExams = userExamMapper.selectByExample(examExample);
        return mpUserExams;
    }

    /**
     * 模拟在线考试
     *
     * @param id 考试id
     * @return
     */
    @Override
    public ApiResult selectSimulation(Long id) {
        List<AppQuestionVo> examCount = new ArrayList<>();
        //题库练习报文
        AppQuestionBankExamVo bankPracticeVo = new AppQuestionBankExamVo();
        //1.查询试卷信息 ，通过考试类型和展示范围查询
        List<MpExamination> mpExaminations = whereSelectExamByAuth(id);
        //2.set试卷到报文里
        bankPracticeVo.setId(mpExaminations.get(0).getId());
        bankPracticeVo.setName(mpExaminations.get(0).getName());
        bankPracticeVo.setTimeLengh(mpExaminations.get(0).getTimeLengh());
        //3.获取该试卷下所有的题库
        QuestionBankAddAndUpdateDto dto = new QuestionBankAddAndUpdateDto();
        dto.setExaminationId(mpExaminations.get(0).getId());
        List<MpQuestionBank> mpQuestionBanks = questionService.selectByExIdAndType(dto);
        log.info("试卷下题库================{}", JSONObject.toJSON(mpQuestionBanks));
        //4.通过type进行分组
        //分组
        Map<Integer, List<MpQuestionBank>> groupByType = mpQuestionBanks.stream().collect(Collectors.groupingBy(MpQuestionBank::getType));
        //遍历分组
        List<AppTypesVo> appTypesVos = new ArrayList<>();
        for (Map.Entry<Integer, List<MpQuestionBank>> entryUser : groupByType.entrySet()) {
            AppTypesVo appTypesVo = new AppTypesVo();
            List<AppQuestionVo> appQuestionVos = new ArrayList<>();
            Integer type = entryUser.getKey();//type值
            //开始放=========================================题库
            List<MpQuestionBank> entryUserList = entryUser.getValue();//试卷下的题库（分组 选项）
            //抽题
            RandomNumVo randomNumVo = randomNum(entryUserList, type, mpExaminations.get(0));
            AppTypesVo.Sum sum = new AppTypesVo.Sum();
            if (type == 1) {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            } else if (type == 2) {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            } else {
                sum.setNum(randomNumVo.getNum());
                sum.setPoints(randomNumVo.getPontins());
                sum.setSum(randomNumVo.getTotalPotins());
            }
            //需要随机去除单选多选判断提数
            //抽取的题数
            List<MpQuestionBank> questionBanks = randomNumVo.getMpQuestionBanks();
            questionBanks.forEach(e -> {
                AppQuestionVo vo = new AppQuestionVo();
                vo.setExaminationId(e.getExaminationId());
                vo.setId(e.getId());
                vo.setName(e.getName());
//                vo.setRightAnswer(Arrays.asList(e.getRightAnswer().toUpperCase().split(",")));
                //找选项===============================开始
                List<AppOptionVo> appOptionVos = new ArrayList<>();
                List<MpOption> mpOptions = optionService.selectByQuestionId(e.getId());
                mpOptions.forEach(i -> {
                    AppOptionVo optionVo = new AppOptionVo();
                    optionVo.setId(i.getId());
                    optionVo.setOpt(i.getOpt().toUpperCase());
                    optionVo.setOptionName(i.getOptionName());
                    optionVo.setQuestionId(i.getQuestionId());
                    appOptionVos.add(optionVo);
                });
                //选项需要排序
                //单字段排序，根据id排序
                appOptionVos.sort(Comparator.comparing(AppOptionVo::getOpt));
                vo.setAppOptionVos(appOptionVos);
                appQuestionVos.add(vo);
            });
            //计算题目总数量
            examCount.addAll(appQuestionVos);
            appTypesVo.setCount(sum);
            appTypesVo.setType(type);
            appTypesVo.setAppQuestionVos(appQuestionVos);
            appTypesVos.add(appTypesVo);
        }
        //试卷分数
        bankPracticeVo.setTotalPoints(mpExaminations.get(0).getPaper());
        //题目数量
        bankPracticeVo.setExamCount(examCount.size());
        appTypesVos.sort(Comparator.comparing(AppTypesVo::getType));
        bankPracticeVo.setAppTypesVos(appTypesVos);
        return ApiResult.success(bankPracticeVo);
    }

    /**
     * 在线模拟考试交卷
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResult submitSimulation(SubmitSlimylationDto dto) {
        if (dto.getQuaestVos().isEmpty()) {
            throw new BusinessException("没有进行答题");
        }
        //当是考试的时候判断考试次数
        MpExamination examination = examinationMapper.selectByPrimaryKey(dto.getExamId());
        //查看考试次数
        int count = userExamService.selectExamByCount(dto.getExamId(), dto.getUserId());
        if (dto.getExamType().intValue() == 2 && examination.getFrequencyCount() <= count) {
            return ApiResult.error(500, "考试次数以上限无法参加考试");
        }
        //计算考试分数
        long id = SnowFlakeUtils.getFlowIdInstance().nextId();
        //成绩-------------------------
        ChengjiVo chengji = chengji(dto);
        MpExamAchievement examAchievement = new MpExamAchievement();
        examAchievement.setCrateUser(dto.getUserId());
        examAchievement.setCreateTime(new Date());
        examAchievement.setDeleFlag(CommonEnum.USED.getCode());
        examAchievement.setExamAchievement(chengji.getSum());
        examAchievement.setExamId(dto.getExamId());
        examAchievement.setExamTime(dto.getExamTime());
        examAchievement.setId(id);
        examAchievement.setIfWhether(chengji.getIfWhere());
        examAchievement.setType(dto.getExamType());
        examAchievement.setUserId(dto.getUserId());
        int i = achievementMapper.insertSelective(examAchievement);
        if (i <= CommonEnum.ADD_ERROR.getCode()) {
            throw new BusinessException("成绩新增失败");
        }
        //获取前端传过来的题库信息和 选项信息
        List<SubmitSlimylationDto.QuaestVo> quaestVos = dto.getQuaestVos();
        //将考试信息存入到考试表中
        quaestVos.forEach(e -> {
            MpUserExam mpUserExam = new MpUserExam();
            mpUserExam.setCreateTime(new Date());
            mpUserExam.setCreateUser(dto.getUserId());
            mpUserExam.setDeleFlag(CommonEnum.USED.getCode());
            mpUserExam.setExamId(dto.getExamId());
            mpUserExam.setId(SnowFlakeUtils.getFlowIdInstance().nextId());
            StringBuffer stringBuffer = new StringBuffer();
            e.getOptionId().stream().forEach(s -> {
                stringBuffer.append(s).
                        append(",");
            });
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            mpUserExam.setOptionId(stringBuffer.toString());
            mpUserExam.setQuestionId(e.getId());
            mpUserExam.setType(e.getType());
            mpUserExam.setUpdateTime(new Date());
            mpUserExam.setUpdateUser(dto.getUserId());
            mpUserExam.setUserId(dto.getUserId());
            mpUserExam.setAchievementId(id);
            mpUserExam.setTypeExam(dto.getExamType());
            int result = userExamMapper.insertSelective(mpUserExam);
            if (result <= CommonEnum.ADD_ERROR.getCode()) {
                throw new BusinessException("考试user记录新增失败");
            }
        });
        SubimtExamVo vo = new SubimtExamVo();
        vo.setExamAchievement(chengji.getSum());
        vo.setJudgeNum(chengji.getJud());
        vo.setMultipleChoiceNum(chengji.getMultple());
        vo.setSingleChoiceNum(chengji.getSigele());

        return ApiResult.success(vo);

    }

    /**
     * 成绩查询
     *
     * @param userId
     * @param id
     * @return
     */
    @Override
    public ApiResult searchGrade(Long userId, Long id) {
        List<SearchGradeVo> searchGradeVos = new ArrayList<>();
        //通过用户id和试卷id查询成绩
        MpExamAchievementExample examAchievementExample = new MpExamAchievementExample();
        MpExamAchievementExample.Criteria criteria = examAchievementExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andExamIdEqualTo(id);
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamAchievement> mpExamAchievements = achievementMapper.selectByExample(examAchievementExample);
        mpExamAchievements.forEach(e -> {
            SearchGradeVo gradeVo = new SearchGradeVo();
            //通过id获取试卷信息
            MpExamination examination = examinationMapper.selectByPrimaryKey(id);
            gradeVo.setId(id);
            gradeVo.setGrageId(e.getId());
            gradeVo.setName(examination.getName());
            gradeVo.setPassingMark(examination.getPassingMark());
            gradeVo.setStartTime(e.getCreateTime());
            gradeVo.setType(e.getType());
            gradeVo.setGrage(e.getExamAchievement());
            gradeVo.setUseTime(e.getExamTime());
            searchGradeVos.add(gradeVo);
        });
        return ApiResult.success(searchGradeVos);
    }


    /**
     * 计算试卷分数
     */


    public ChengjiVo chengji(SubmitSlimylationDto dto) {
        ChengjiVo chengjiVo = new ChengjiVo();
        //通过试卷id获取试卷相关信息
        MpExaminationExample examinationExample = new MpExaminationExample();
        MpExaminationExample.Criteria criteria = examinationExample.createCriteria();
        criteria.andIdEqualTo(dto.getExamId());
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExamination> mpExaminations = examinationMapper.selectByExample(examinationExample);
        MpExamination examination = mpExaminations.get(0);
        //获取试卷分数
        Integer paper = examination.getPaper();
        //获取提醒的相关分数
        MpExaminationRuleExample ruleExample = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria rulec = ruleExample.createCriteria();
        rulec.andExamIdEqualTo(dto.getExamId());
        rulec.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        List<MpExaminationRule> mpExaminationRules = examinationRuleMapper.selectByExample(ruleExample);
        //分别获取对象题目的分数
        List<MpExaminationRule> singles = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.SINGLE.getCode())).collect(Collectors.toList());
        List<MpExaminationRule> mult = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.MULTIPLE.getCode())).collect(Collectors.toList());
        List<MpExaminationRule> judge = mpExaminationRules.stream().filter(a -> a.getSubjectName().equals(TypeEnum.JUDGE.getCode())).collect(Collectors.toList());
        //开始计算错题数量
        //获取所有选项的id和题库的id
        List<SubmitSlimylationDto.QuaestVo> quaestVos = dto.getQuaestVos();
        AtomicReference<Integer> singlesCount = new AtomicReference<>(0);
        AtomicReference<Integer> multCount = new AtomicReference<>(0);
        AtomicReference<Integer> judgeCount = new AtomicReference<>(0);
        //------------------------------正确的数量-------------------------
        AtomicReference<Integer> rSsinglesCount = new AtomicReference<>(0);
        AtomicReference<Integer> rMultCount = new AtomicReference<>(0);
        AtomicReference<Integer> rJudgeCount = new AtomicReference<>(0);
        for (SubmitSlimylationDto.QuaestVo quaestVo : quaestVos) {
            QuestionSearchVo vo = questionService.searchById(quaestVo.getId());
            Integer type = vo.getType();
            //通过前端传过来的选项id集合查询出选项
            List<MpOption> mpOptions = optionService.selectByIds(quaestVo.getOptionId());
            //提取所有的选项（用户选的）
            List<String> ops = mpOptions.stream().map(MpOption::getOpt).collect(Collectors.toList());
            //获取本题的答案和选项名称
            String rightAnswer = vo.getRightAnswer();
            //将答案转换成list集合
            List<String> rightAnswers = Arrays.asList(rightAnswer.toUpperCase().split(","));

            //处理大小写问题
            List<String> trueResult = new ArrayList<>();
            List<String> opsResult = new ArrayList<>();
            rightAnswers.forEach(e -> {
                String s = e.toUpperCase();
                trueResult.add(s);
            });
            ops.forEach(e -> {
                String s = e.toUpperCase();
                opsResult.add(s);
            });
            boolean result = trueResult.containsAll(opsResult) && opsResult.containsAll(trueResult)
                    && trueResult.size() == opsResult.size();
            if (!result) {
                switch (type) {
                    //单选
                    case 1:
                        //开始判断
                        singlesCount.getAndSet(singlesCount.get() + 1);
                        break;
                    case 2:
                        //开始判断
                        multCount.getAndSet(multCount.get() + 1);
                        break;
                    default:
                        judgeCount.getAndSet(judgeCount.get() + 1);
                        break;
                }
            }
            //-----------------------------------------正确的数量
            if (result) {
                switch (type) {
                    //单选
                    case 1:
                        //开始判断
                        singlesCount.getAndSet(rSsinglesCount.get() + 1);
                        break;
                    case 2:
                        //开始判断
                        multCount.getAndSet(rMultCount.get() + 1);
                        break;
                    default:
                        judgeCount.getAndSet(rJudgeCount.get() + 1);
                        break;
                }
            }

        }
        //计算中分
        MpExaminationRule singleExaminationRule = singles.get(0);
        Integer sfraction = singleExaminationRule.getFraction();
        MpExaminationRule multEx = mult.get(0);
        Integer mfraction = multEx.getFraction();
        MpExaminationRule examinationRule = judge.get(0);
        Integer jfraction = examinationRule.getFraction();
        Integer sigele = sfraction * singlesCount.get();
        Integer multple = mfraction * multCount.get();
        Integer jud = jfraction * judgeCount.get();
        Integer sum = paper - (sigele + multple + jud);
        chengjiVo.setSum(sum);
        chengjiVo.setSigele(sfraction * rSsinglesCount.get());
        chengjiVo.setMultple(mfraction * rMultCount.get());
        chengjiVo.setJud(jfraction * rJudgeCount.get());
        if (examination.getPassingMark() <= sum) {
            chengjiVo.setIfWhere(CommonEnum.IF_WHERE.getCode());
        } else {
            chengjiVo.setIfWhere(CommonEnum.NOT_IF_WHERE.getCode());
        }
        return chengjiVo;
    }

//    public static void main(String[] args) {
//        AtomicReference<Integer> singlesCount = new AtomicReference<>(0);
//        for (int i = 0; i <10 ; i++) {
//            singlesCount.getAndSet(singlesCount.get() + 1);
//        }
//        System.out.println(singlesCount.get());
//    }

    /**
     * entryUserList 一定是大于抽题数量的
     *
     * @param entryUserList  试卷下的题没有随机的
     * @param type           类型a
     * @param mpExaminations 试卷信息
     */
    public RandomNumVo randomNum(List<MpQuestionBank> entryUserList, Integer type, MpExamination mpExaminations) {
        RandomNumVo randomNumVo = new RandomNumVo();
        MpExaminationRuleExample example = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria criteria = example.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andExamIdEqualTo(mpExaminations.getId());
        List<MpQuestionBank> randomList = new ArrayList<>();
        List<MpExaminationRule> mpExaminationRules = new ArrayList<>();
        //获取抽题数量
        switch (type) {
            //单选
            case 1:
                criteria.andSubjectNameEqualTo(TypeEnum.SINGLE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
                if (entryUserList.size() < mpExaminationRules.get(0).getSubjectNum()) {
                    throw new BusinessException("抽题数大于题数！");
                }
                randomList = getRandomList(entryUserList, mpExaminationRules.get(0).getSubjectNum());
                randomNumVo.setMpQuestionBanks(randomList);
                randomNumVo.setNum(randomList.size());
                randomNumVo.setType(1);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * randomList.size());
                break;
            case 2:
                criteria.andSubjectNameEqualTo(TypeEnum.MULTIPLE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
                if (entryUserList.size() < mpExaminationRules.get(0).getSubjectNum()) {
                    throw new BusinessException("抽题数大于题数！");
                }
                randomList = getRandomList(entryUserList, mpExaminationRules.get(0).getSubjectNum());
                randomNumVo.setMpQuestionBanks(randomList);
                randomNumVo.setNum(randomList.size());
                randomNumVo.setType(2);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * randomList.size());
                break;
            default:
                criteria.andSubjectNameEqualTo(TypeEnum.JUDGE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
                if (entryUserList.size() < mpExaminationRules.get(0).getSubjectNum()) {
                    throw new BusinessException("抽题数大于题数！");
                }
                randomList = getRandomList(entryUserList, mpExaminationRules.get(0).getSubjectNum());
                randomNumVo.setMpQuestionBanks(randomList);
                randomNumVo.setNum(randomList.size());
                randomNumVo.setType(3);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * randomList.size());
                break;
        }
        return randomNumVo;
    }

    public RandomNumVo datiNum(List<MpUserExam> value, Integer type, MpExamination mpExaminations) {
        //
        List<Long> ids = value.stream().map(MpUserExam::getQuestionId).collect(Collectors.toList());
        List<MpQuestionBank> entryUserList = questionService.selectByIds(ids);
        RandomNumVo randomNumVo = new RandomNumVo();
        MpExaminationRuleExample example = new MpExaminationRuleExample();
        MpExaminationRuleExample.Criteria criteria = example.createCriteria();
        criteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        criteria.andExamIdEqualTo(mpExaminations.getId());
//        List<MpQuestionBank> randomList = new ArrayList<>();
        List<MpExaminationRule> mpExaminationRules = new ArrayList<>();
        //获取抽题数量
        switch (type) {
            //单选
            case 1:
                criteria.andSubjectNameEqualTo(TypeEnum.SINGLE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
//                randomList = getRandomList(entryUserList, mpExaminations.getSingleChoiceNum());
                randomNumVo.setMpQuestionBanks(entryUserList);
                randomNumVo.setNum(entryUserList.size());
                randomNumVo.setType(1);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * entryUserList.size());
                break;
            case 2:
                criteria.andSubjectNameEqualTo(TypeEnum.MULTIPLE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
//                entryUserList = getRandomList(entryUserList, mpExaminations.getMultipleChoiceNum());
                randomNumVo.setMpQuestionBanks(entryUserList);
                randomNumVo.setNum(entryUserList.size());
                randomNumVo.setType(2);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * entryUserList.size());
                break;
            default:
                criteria.andSubjectNameEqualTo(TypeEnum.JUDGE.getCode());
                mpExaminationRules = examinationRuleMapper.selectByExample(example);
//                randomList = getRandomList(entryUserList, mpExaminations.getJudgeNum());
                randomNumVo.setMpQuestionBanks(entryUserList);
                randomNumVo.setNum(entryUserList.size());
                randomNumVo.setType(3);
                randomNumVo.setPontins(mpExaminationRules.get(0).getFraction());
                randomNumVo.setTotalPotins(mpExaminationRules.get(0).getFraction() * entryUserList.size());
                break;
        }
        return randomNumVo;
    }


    public List<MpQuestionBank> getRandomList(List<MpQuestionBank> entryUserList, Integer count) {
        if (count == 0) {
            return new ArrayList<MpQuestionBank>();
        }
        Random random = new Random();
        ArrayList<Integer> integers = new ArrayList<>();//临时存放产生的list索引，去除重复的索引
        ArrayList<MpQuestionBank> objects = new ArrayList<>();//生成新的list
        int temp = 0;
        if (count <= 1) {//如果数据小于等于一，去一条数据
            temp = random.nextInt(entryUserList.size());
            objects.add(entryUserList.get(temp));
        } else {
            for (int i = 0; i < Math.ceil(count); i++) {
                temp = random.nextInt(entryUserList.size());
                if (!integers.contains(temp)) {
                    integers.add(temp);
                    objects.add(entryUserList.get(temp));
                } else {
                    i--;
                }
            }
        }
        return objects;
    }

    public List<MpQuestionBank> selectErrorQuestion(Long userId, Long examId) {
        //通过用户id和试卷id查询错题信息
        MpErrorQuestionExample questionExample = new MpErrorQuestionExample();
        MpErrorQuestionExample.Criteria errorCriteria = questionExample.createCriteria();
        errorCriteria.andDeleFlagEqualTo(CommonEnum.USED.getCode());
        errorCriteria.andUserIdEqualTo(userId);
        errorCriteria.andExamIdEqualTo(examId);
        List<MpErrorQuestion> mpErrorQuestions = mpErrorQuestionMapper.selectByExample(questionExample);
        //去重
        List<MpErrorQuestion> distinctClass = mpErrorQuestions.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getQuestionId()))), ArrayList::new));
        List<MpQuestionBank> questionBanks = new ArrayList<>();
        if (!distinctClass.isEmpty()) {
            //提取题库id
            List<Long> ids = distinctClass.stream().map(MpErrorQuestion::getQuestionId).collect(Collectors.toList());
            questionBanks = questionService.selectByIds(ids);
            return questionBanks;
        }
        return questionBanks;
    }

}
