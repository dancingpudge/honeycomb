# 标准返回格式
    在项目开发过程，尤其是通过FeignClient来进行为服务之间的调用。统一的参数传递和返回使用统一的标准，有利于团队的协作和代码的维护。
    参数的传递和校验我们已经使用了javax.validation和org.hibernate来进行参数校验，并使用了swagger3来统一接口规范。
    接口风格尽可能的按照restful。但是，并没有统一的错误处理机制。现尝试定义统一的规范，希望初期能激起讨论，后期采用一致规范。

## StandardResultVO
### 1、成功时
返回StandardResultVO.successResult(T)
* 注意：<br />
  A 当返回普通对象时可以返回null<br />
  B 当返回结果是集合类型时，如果为空则必须返回空集合<br />
  C 调用方通过StandardResultVO.success()，来判断下游系统是否调用成功
#### 示例
  ````Java
    @ApiOperation(value = "提供查询bucket的接口，传入设备ID，类型、后查出最新的bucket信息")
    @PostMapping(value = "/find_bucket/by_detector")
    public StandardResultVO<BucketVO> findByDetector(@Validated @RequestBody BucketDetectorDTO bucketDetectorDTO) {
        Detector detectorByDetectorNo = detectorService.getDetectorByDetectorNo(bucketDetectorDTO.getDetectorNo());
        Bucket bucket = bucketService.getById(detectorByDetectorNo.getBucketId());
        return StandardResultVO.successResult(BeanCopierUtil.initCopy(bucket,BucketVO.class));
    }

    @ApiOperation(value = "提供查询bucketNo的接口，车位编号后查出最新的bucket信息")
    @PostMapping(value = "/find_bucket_id/by_parking_space_no")
    public StandardResultVO<Integer> findByParkingSpaceNo(
            @ApiParam(value = "车位编号", defaultValue = "0") @RequestParam(name = "parking_space_no") String parkingSpaceNo) {
        BucketParkingSpace bucketParkingSpace = bucketParkingSpaceService.getByParkingSpaceNo(parkingSpaceNo);
        return StandardResultVO.successResult(Optional.ofNullable(bucketParkingSpace).isPresent() ? bucketParkingSpace.getBucketId() : 0);
    }
  ````
    
### 2、失败时
通过抛出ApiException来处理业务异常。需要各自项目维护api-error.properties（错误码与错误信息）。<br />
错误码和错误信息，由拦截器统一包装传递结果到调用方。<br />

注意：<br />
A 错误码统一分配，参看错误码分配表
B api-error.properties放在resources目录下
C 不限制在controller还是service中抛出
#### 示例
 ````Java
    //api-error.properties

    @ApiOperation("新增车场")
    @PostMapping("/car_parks")
    public StandardResultVO<Boolean> create(@RequestBody @Validated({CreateGroup.class, Default.class}) CarParkInitDTO dto) throws InterruptedException {
      Boolean unique = carParkService.checkCarParkNoUnique(dto.getCarParkNo());
      if (!unique) {
        throw new ApiException("10001");
      }
      return StandardResultVO.successResult(carParkService.create(dto));
    }

    @Override
    protected void dataCheck(JSONObject kafkaMsg) {
        //检查业务数据是否已处理
        String correctId = kafkaMsg.getJSONObject("data").getString("correct_id");
        EvidencePO evidencePO = evidenceDAO.findOneByCorrectId(correctId);
        if (Optional.ofNullable(evidencePO).isPresent()) {
            throw new ApiException("10001");
        }
    }
 ````



## StandardPageResultVO
### 1、成功时
  PageUtil pageUtil = new PageUtil(carParkPage.getCurrent(), carParkPage.getSize());
  pageUtil.setTotalRows(carParkPage.getTotal());
  StandardPageResultVO.successResult(pageUtil, List<VO>);

* 注意：<br />
  A 不可返回null<br />
  B 如果为空则必须返回空集合<br />
  C 调用方通过StandardPageResultVO.success()，来判断下游系统是否调用成功

#### 示例
 ````Java
  @ApiOperation("视频杆查询列表(分页)")
  @PostMapping("/pole/page")
  public StandardPageResultVO<List<PoleVO>> getPolePage(@RequestBody PageForm pageForm) {
    Page page = PageUtils.pageForm2Page(pageForm);
    IPage<Pole> polePage = poleService.getPolePage(page);
    List<PoleVO> list = BeanCopierUtil.copyList(polePage.getRecords(), PoleVO.class);
    .....
    PageUtil pageUtil = new PageUtil(polePage.getCurrent(), polePage.getSize());
    pageUtil.setTotalRows(polePage.getTotal());
    return StandardPageResultVO.successResult(pageUtil, list);
  }
 ````

### 2、失败时
  同StandardResultVO失败时的处理

##计划过时
 ````Java
    @Deprecated
    public static <T> StandardResultVO<T> errorResult() {
      return errorResult("操作失败");
    }
    
    @Deprecated
    public static <T> StandardResultVO<T> errorResult(String message) {
        return errorResult(LOGIC_ERR_CODE, message);
    }
    
    @Deprecated
    public static <T> StandardResultVO<T> errorResult(String errorCode, String message) {
        return new StandardResultVO<T>(errorCode, message, null);
    }
    
    @Deprecated
    public static <T> StandardResultVO<T> successResult(T data, String message) {
        return new StandardResultVO<T>(SUCCESS_CODE, message, data);
    }
    
    
       @Deprecated
    public static <T> StandardPageResultVO<T> errorResult(String message) {
        return new StandardPageResultVO<T>(LOGIC_ERR_CODE, message);
    }

    @Deprecated
    public static <T> StandardPageResultVO<T> errorResult(String errorCode, String message) {
        return new StandardPageResultVO<T>(errorCode, message);
    }

    @Deprecated
    public static <T> StandardPageResultVO<T> successResult(PageUtil pageInfo, T t, String message) {
        return new StandardPageResultVO<T>(SUCCESS_CODE, message, pageInfo, t);
    }
 ````


