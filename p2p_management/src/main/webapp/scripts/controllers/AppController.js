function objToStr(obj) {
	var array = [];
	if (angular.isObject(obj)) {
		for ( var key in obj) {
			array.push(key + '=' + obj[key]);
		}
		return array.join('&');
	} else {
		return obj;
	}
}

function toggle($a) {

	$($a).next().toggle()

}

function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = {
		"Y" : "s",
		"N" : "ps"
	};
	zTree.setting.check.chkboxType = type;

}

function setQxCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = {
		"Y" : "ps",
		"N" : "ps"
	};
	zTree.setting.check.chkboxType = type;

}

angular
		.module('AppController', [])

		.controller(
				'loginCtrl',
				function($rootScope, $scope, $state, AdminService) { // 将AdminService注入到了当前的loginCtrl中。
					$scope.errorMsg = '';

					$scope.login = function(username, password) {
						// 拼接向服务器发送的数据
						var str = 'username=' + username + '&password='
								+ password;
						// alert(username + " " + password);
						AdminService.signIn(str).success(
								function(response) {
									if (response.status == 1) {
										localStorage.setItem("admin_name",
												response.data.username);
										localStorage.setItem("admin_id",
												response.data.id);
										$state.go("home"); // 改变状态
									} else if (response.status == 0) {
										$scope.errorMsg = '用户名或密码错误';
									} else {
										$scope.errorMsg = '用户登录异常，请联系客服！';
									}
								}).error(function() {
							$scope.errorMsg = '网络异常，稍后重试！';
							$scope.refresh();
						});
					};
					$scope.hideMsg = function() {
						$scope.errorMsg = '';
					};
				})

		// 空间首页
		.controller(
				'homeCtrl',
				function($rootScope, $location, $scope, $state, AdminService,
						$compile) {
					$scope.path = $location.path();
					document.getElementById("admin").innerText = localStorage
							.getItem("admin_name")
							+ "";

					var str = 'adminId=' + localStorage.getItem("admin_id");
					AdminService
							.quanxian(str)
							.success(
									function(response) {
										if (response.status == 1) {
											$scope.glsf = response.data.rolelist[0].rolename;

											var html = "";
											for ( var x in response.data.privilegelist) {
												if (response.data.privilegelist[x].type == 1) {

													html += "<li ng-class="
															+ response.data.privilegelist[x].liNgClass
															+ "><a href='javascript:;' rel='title' onclick='toggle(this)'><img src='"
															+ response.data.privilegelist[x].imgsrc
															+ "' width='16' height='14' >&nbsp;"
															+ response.data.privilegelist[x].name
															+ "</a> <ul class='subMenu' style="
															+ response.data.privilegelist[x].ulStyle
															+ ">";

													for ( var y in response.data.privilegelist[x].privilegeChildren) {

														html += "<li><a href='javascript:;' ui-sref='"
																+ response.data.privilegelist[x].privilegeChildren[y].liuisref
																+ "'  ui-sref-active='active'>"
																+ response.data.privilegelist[x].privilegeChildren[y].name
																+ "</a></li>";
													}
													html += "  </ul>  </li>";

												}
											}
											var $html = $compile(html)($scope);
											$("#caidan").html($html);

											var arr = [];
											var len = 30;
											for (var i = 0; i < len; i++) {
												var strTem = '"a' + i
														+ '":true';
												arr.push(strTem)
											}
											var str = "{" + arr.join(',') + '}';

											var obj = eval('(' + str + ')');

											$scope.rights = obj;

											if ($scope.rights.a1
													|| $scope.rights.a2
													|| $scope.rights.a28) {
												$(".menu_list>li").eq(0).css(
														"display", "block");

											}
											if ($scope.rights.a7
													|| $scope.rights.a4
													|| $scope.rights.a5
													|| $scope.rights.a6
													|| $scope.rights.a29) {
												$(".menu_list>li").eq(1).css(
														"display", "block");
											}

											if ($scope.rights.a3) {
												$(".menu_list>li").eq(2).css(
														"display", "block")
											}
											if ($scope.rights.a8
													|| $scope.rights.a9
													|| $scope.rights.a10
													|| $scope.rights.a11
													|| $scope.rights.a30
													|| $scope.rights.a31) {
												$(".menu_list>li").eq(3).css(
														"display", "block")
											}
											if ($scope.rights.a13
													|| $scope.rights.a14
													|| $scope.rights.a15
													|| $scope.rights.a16
													|| $scope.rights.a17
													|| $scope.rights.a18
													|| $scope.rights.a19
													|| $scope.rights.a20) {
												$(".menu_list>li").eq(4).css(
														"display", "block")
											}
											if ($scope.rights.a21
													|| $scope.rights.a22) {
												$(".menu_list>li").eq(5).css(
														"display", "block")
											}
											if ($scope.rights.a23) {
												$(".menu_list>li").eq(6).css(
														"display", "block")
											}
											if ($scope.rights.a24
													|| $scope.rights.a25) {
												$(".menu_list>li").eq(7).css(
														"display", "block")
											}
											if ($scope.rights.a26) {
												$(".menu_list>li").eq(8).css(
														"display", "block")
											}
											if ($scope.rights.a27) {
												$(".menu_list>li").eq(9).css(
														"display", "block")
											}

										}
									})

					// 退出操作
					$scope.logout = function() {
						$rootScope.LoginAdmin = '';
						$state.go('login');
					}
				})
		// 用户列表
		.controller("userListCtrl", function($scope, $state, UserService) {

			// 页面加载过程中，这个函数会执行，得到用户信息
			$scope.getUserList = function() {

				UserService.getUserList().success(function(response) {
					// 响应回的数据格式{status:1,data:[{product对象},{product对象},{product对象}]}
					if (response.status == 1) {
						$scope.info = response.data;
					}
				});
			}
			// 调用函数
			$scope.getUserList();

			// 根据产品ID隐藏删除按钮的判断函数
			$scope.isHide = function(value) {
				// if (value != 1 || value != 2 || value != 3) {
				// return false;
				// } else {
				return true;
				// }
			}

			// 用户删除
			$scope.Del = function(id) {

				var str = 'id=' + id;
				UserService.del(str).success(function(response) {
					if (response.status == 1) {
						$scope.getUserList();
					}
				})

			}

			// 根据ID获取用户信息
			$scope.viewUserById = function(id) {
				var str = 'id=' + id
				UserService.getUserById(str).success(function(response) {
					if (response.status == 1) {
						$scope.view = response.data; // 绑定数据到view

						$scope.colseDown = true;
						$scope.recordList = false;
						$('#myModal_b').modal();
					}

				});
			};

			$scope.hide = function() {
				if (!$scope.recordList) {
					if ($scope.colseDown) {
						$('#myModal_b').modal('hide');
						$scope.recordList = true;
						$scope.colseDown = false;
					} else {
						$scope.recordList = true;
					}
				} else {
					$('#myModal_b').modal('hide');
				}
			}
		})
		// 用户添加
		.controller(
				"userAdd",
				function($rootScope, $scope, $interval, $state, UserService,
						AuthService, PostService, UserService,
						checkParamService2) {
					$scope.save = function() {
						// 应该判断验证是ok，才可以进行注册
						if ($scope.nameIsPass && $scope.phoneIsPass
								&& $scope.yzpassword()
								&& $scope.yzconfirm_password()
								&& $scope.isAgree) {
							// 进行注册
							alert("ok");
							// 得到所有请求参数
							var data = {
								username : $scope.username, // 用户名
								phone : $scope.phone, // 电话
								password : $scope.password, // 密码
								signUuid : $scope.uuid, // uuid
								signCode : $scope.captcha
							// 验证码
							};

							// 得到所有要向服务器发送的参数
							var _params = objToStr(data); // username=xxx&phone=xxx&password=xxx
							// 完成注册操作
							UserService
									.register(encodeURI(_params))
									.success(
											function(res) {
												if (res.status == 1) {
													$rootScope.loginName = $scope.username;
													AuthService.setCookie(
															'user',
															$scope.username);
													AuthService.setCookie(
															'uid', res.data.id);
													AuthService
															.setToken(res.token);
													$scope.two_step = true;
													$scope.three_step = true;
													$scope.four_step = false;

													var _time = 3;
													$scope.times = 3;
													var _timer = $interval(
															function() {
																if (_time > 0) {
																	--_time;
																	$scope.times = _time;
																} else {
																	if (angular
																			.isDefined(_timer)) {
																		$interval
																				.cancel(_timer);
																	}
																	$state
																			.go("home.userlist");
																}
															}, 1000);

												} else {
													$scope.regMsg = hmd
															.presentStatus(res.status)
													return false;
												}
											});
						}

					}
					$scope.cancel = function() {
						$state.go("home.userlist");
					}
					// 验证用户名
					$scope.yzusername = function() {
						var obj = checkParamService2
								.checkUname($scope.username);
						if (obj.isTrue) {
							var data = {
								username : $scope.username
							};
							PostService.checkUserName(objToStr(data)).success(
									function(res) {
										if (res.status == 1) {
											$scope.username_error_msg = '';
											$scope.nameIsPass = true;
										} else {
											$scope.username_error_msg = hmd
													.presentStatus(res.status);
											$scope.nameIsPass = false;
										}
									});
						} else {
							$scope.username_error_msg = obj.msg;
							$scope.nameIsPass = obj.isTrue;
						}
					}

					// 手机号码验证
					$scope.phone = '';
					$scope.yzphone = function() {
						// 验证手机号码不为空
						if (/^\s*$/.test($scope.phone)) {
							$scope.phone_error_msg = '请输入您的手机号！';
							$scope.phoneIsPass = false;
							// 验证手机号码长度为11,并且是符合规则的
						} else if (!(/^1[34578]\d{9}$/.test($scope.phone))) {
							$scope.phone_error_msg = '手机号码格式不正确！';
							$scope.phoneIsPass = false;
						} else {
							var data = {
								phone : $scope.phone
							};
							PostService
									.checkPhone(objToStr(data))
									.success(
											function(res) {
												if (res.status == 1) {
													$scope.phone_error_msg = '';
													$scope.phoneIsPass = true;
												} else {
													$scope.phone_error_msg = '手机号已存在,请重新输入！';
													$scope.phoneIsPass = false;
												}
											});
						}
					};
					$scope.password = '';
					$scope.strong = 1;
					// 验证密码
					$scope.yzpassword = function() {
						var obj = checkParamService2.checkPwd($scope.password);
						$scope.pwd_error_msg = obj.msg;
						$scope.strong = obj.strong; // 描述的密码强度
						return obj.isTrue;
					};
					$scope.confirm_password = '';
					// 验证确认密码
					$scope.yzconfirm_password = function() {
						var obj = checkParamService2.checkRePwd(
								$scope.password, $scope.confirm_password);
						$scope.repwd_error_msg = obj.msg;
						return obj.isTrue;
					};

					// 获取验证码
					$scope.uuid = '';
					PostService.getUuid().success(function(res) {
						if (res.status == 1) {
							$scope.uuid = res.uuid;
							$scope.img_src = PostService.getImg($scope.uuid);
						}
					});

					$scope.isPassCaptcha = false;
					$scope.code_error_msg = '';
					$scope.authCaptcha = function() {
						if (/^\s*$/.test($scope.captcha)) {
							$scope.code_error_msg = '请输入验证码！';
							$scope.isPassCaptcha = false;
						} else if (!/[0-9a-zA-Z]/.test($scope.captcha)) {
							$scope.code_error_msg = '验证码为数字或字母组合！';
							$scope.isPassCaptcha = false;
						} else {
							var data = {
								signUuid : $scope.uuid,
								signCode : $scope.captcha
							};
							PostService
									.yzAuthCaptcha(objToStr(data))
									.success(
											function(res) {
												if (res.status == 1) {
													$scope.code_error_msg = '';
													$scope.isPassCaptcha = true;
												} else {
													$scope.code_error_msg = '验证码输入有误，请重新输入！';
													$scope.isPassCaptcha = false;
												}
											});
						}
					};

					$scope.refresh = function() {
						$scope.isPassCaptcha = false;
						$scope.captcha = '';
						$scope.img_src = PostService.getImg($scope.uuid);
					};

					$scope.phoneCodeMsg = '';
					$scope.getauthcode = function() {
						$scope.reaccept = true;
						var maxtime = 60;
						$scope.msg = maxtime + "秒后重新获取";
						var timer = $interval(function() {
							if (maxtime > 0) {
								--maxtime;
								$scope.msg = maxtime + "秒后重新获取";
							} else {
								$scope.msg = '';
								$scope.reaccept = false;
								if (angular.isDefined(timer)) {
									$interval.cancel(timer);
								}
							}
						}, 1000);
						var data = {
							phone : $scope.phone
						};
						PostService.getPhoneCode(objToStr(data)).success(
								function(res) {
									if (res.status == 1) {
										$scope.phoneCodeMsg = '验证码已发送';
									} else {
										$scope.phoneCodeMsg = '验证码发送失败，请稍后重试！';
									}
								});
					}
				})
		// 修改用户
		.controller(
				"userEdit",
				function($scope, $state, $stateParams, UserService) {

					var id = $stateParams.id;
					var str = "id=" + id

					$scope.userInfo = "";
					// 调用函数,商品信息回显
					(function() {
						UserService.getUserById(str).success(
								function(response) {
									if (response.status == 1) {
										$scope.userInfo = response.data;
									}
								})
					})();

					// 真正提交修改
					$scope.commitUser = function(id) {
						var str = "";
						for ( var x in $scope.userInfo) {

							str += "&" + x + "=" + $scope.userInfo[x];

						}
						if (str.substring(0, 1) === '&') {
							str = str.substring(1);
						}
						alert(str);

						UserService.commitUser(str).success(function(response) {
							if (response.status == 1) {
								$state.go("home.userlist");
							}
						});

					}

					$scope.cancel = function() {
						$state.go("home.userlist");
					}

				})

		// 产品列表
		.controller("productList", function($scope, $state, ProductService) {

			// 页面加载过程中，这个函数会执行
			$scope.getProductList = function() {

				ProductService.getProductList().success(function(response) {
					// 响应回的数据格式{status:1,data:[{product对象},{product对象},{product对象}]}
					if (response.status == 1) {
						$scope.info = response.data;
					}
				});
			}
			// 调用函数
			$scope.getProductList();

			// 根据产品ID隐藏删除按钮的判断函数
			$scope.isHide = function(value) {
				// if (value != 1 || value != 2 || value != 3) {
				// return false;
				// } else {
				return true;
				// }
			}

			// 产品删除
			$scope.productDel = function(proId) {

				var str = 'proId=' + proId;
				ProductService.delProduct(str).success(function(response) {
					if (response.status == 1) {
						$scope.getProductList();
					}
				})

			}

			// 根据ID获取产品信息
			$scope.viewProductById = function(proId) {
				var str = 'pid=' + proId
				alert(str)
				ProductService.getProductById(str).success(function(response) {
					if (response.status == 1) {
						$scope.view = response.data; // 绑定数据到view
						alert(JSON.stringify(response.data))
						$scope.colseDown = true;
						$scope.recordList = false;
						$('#myModal_b').modal();
					}

				});
			};

			$scope.hide = function() {
				if (!$scope.recordList) {
					if ($scope.colseDown) {
						$('#myModal_b').modal('hide');
						$scope.recordList = true;
						$scope.colseDown = false;
					} else {
						$scope.recordList = true;
					}
				} else {
					$('#myModal_b').modal('hide');
				}
			}
		})
		// 产品添加
		.controller(
				"productAdd",
				function($scope, $state, ProductService) {
					$scope.saveProduct = function() {
						var str = "";
						for ( var x in $scope.productInfo) {
							if (x === "proEarningRate") {
								var rateStr = "";
								for (var y = 0; y < $scope.productInfo.proEarningRate.length; y++) {
									rateStr += '"'
											+ $scope.productInfo.proEarningRate[y].month
											+ '":'
											+ $scope.productInfo.proEarningRate[y].incomeRate
											+ ","
								}
								rateStr = '{'
										+ rateStr.substr(0, rateStr.length - 1)
										+ '}';
								str += "&proEarningRates=" + rateStr;
							} else {
								str += "&" + x + "=" + $scope.productInfo[x];
							}
						}
						if (str.substring(0, 1) === '&') {
							str = str.substring(1);
						}
						alert(str);

						ProductService.saveProduct(str).success(
								function(response) {
									if (response.status == 1) {
										$state.go("home.productlist");
									}
								})

					}

					$scope.cancelProduct = function() {
						$state.go("home.productlist");
					}
					// 设置利率
					$scope.addEarningRate = function() {
						var options = {
							backdrop : "static"
						};
						$('#myModal_b').modal(options);

					}
					// 添加月利率设置
					$scope.addField = function() {

						if ($scope.productInfo.proEarningRate == null) {
							$scope.productInfo.proEarningRate = [ {
								"month" : $scope.rate.month,
								"incomeRate" : $scope.rate.incomeRate
							} ];
						} else {
							$scope.productInfo.proEarningRate.push({
								"month" : $scope.rate.month,
								"incomeRate" : $scope.rate.incomeRate
							});
						}

					}
				})
		// 修改产品
		.controller(
				"productEdit",
				function($scope, $state, $stateParams, ProductService) {

					var proId = $stateParams.proId;
					var str = "pid=" + proId

					$scope.productInfo = "";
					// 调用函数,商品信息回显
					(function() {
						ProductService.getProductById(str).success(
								function(response) {
									if (response.status == 1) {
										$scope.productInfo = response.data;
									}
								})
					})();

					// 真正提交修改
					$scope.commitProduct = function(proId) {
						var str = "";
						for ( var x in $scope.productInfo) {
							if (x === "proEarningRate") {
								var rateStr = "";
								for (var y = 0; y < $scope.productInfo.proEarningRate.length; y++) {
									rateStr += '"'
											+ $scope.productInfo.proEarningRate[y].month
											+ '":'
											+ $scope.productInfo.proEarningRate[y].incomeRate
											+ ","
								}
								rateStr = '{'
										+ rateStr.substr(0, rateStr.length - 1)
										+ '}';
								str += "&proEarningRates=" + rateStr;
							} else {
								str += "&" + x + "=" + $scope.productInfo[x];
							}
						}
						if (str.substring(0, 1) === '&') {
							str = str.substring(1);
						}
						alert(str);

						ProductService.commitProduct(str).success(
								function(response) {
									if (response.status == 1) {
										$state.go("home.productlist");
									}
								});

					}

					$scope.cancelProduct = function() {
						$state.go("home.productlist");
					}

					// 设置利率
					$scope.change = function(proId) {
						var str = 'proId=' + proId;
						// 获取当前利率
						ProductService
								.getRatesById(str)
								.success(
										function(response) {
											if (response.status == 1) {
												$scope.productInfo.proEarningRate = response.data;

												var options = {
													backdrop : "static"
												};
												$('#myModal_b').modal(options);
											}
										});
					}
				})

		// 债权录入
		.controller(
				'entering',
				function($scope, $state, AuthService, PostService, hmd,
						checkParamService, $stateParams) {

					$scope.alertMsgs = []
					$scope.params = {
						// 默认值
						contractNo : undefined, // 借款Id（合同编号）
						debtorsName : undefined, // 债务人
						debtorsId : undefined, // 身份证号
						loanPurpose : undefined, // 借款用途
						loanType : undefined, // 借款类型（标的类型）
						loanPeriod : undefined, // 原始期限（月）源
						loanStartDate : undefined, // 原始借款开始日期
						loanEndDate : undefined, // 原始借款到期日期
						repaymentStyle : 11601, // 还款方式 radius
						repaymenMoney : undefined, // 期供金额（元）
						debtMoney : undefined, // 债权金额（元）
						debtMonthRate : undefined, // 债权年化利率（%// ）
						debtTransferredMoney : undefined, // 债权转入金额
						debtTransferredPeriod : undefined, // 债权可用期（月）
						debtTransferredDate : undefined, // 债权转入日期
						debtRansferOutDate : undefined, // 债权转出日期
						creditor : undefined
					// 债权人
					}

					function getDateVal() {
						$scope.params.loanStartDate = $("#dLoanStartDate")
								.val()
						$scope.params.loanEndDate = $("#dLoanEndDate").val()
						$scope.params.debtTransferredDate = $(
								"#dDebtTransferredDate").val()
						$scope.params.debtRansferOutDate = $(
								"#dDebtRansferOutDate").val()
					}
					// 表单验证
					function formInvail() {
						var msg = []
						var debtorsId = $("#dDebtorsId").val(), loanPeriod = $(
								"#dLoanPeriod").val(), debtMoney = $(
								"#dDebtMoney").val(), repaymenMoney = $(
								"#dRepaymenMoney").val(), debtMonthRate = $(
								"#dDebtMonthRate").val(), debtTransferredMoney = $(
								"#dDebtTransferredMoney").val(), debtTransferredPeriod = $(
								"#dDebtTransferredPeriod").val();
						if (!checkParamService.AuthIDcard(debtorsId)) {
							msg.push("身份证号码格式不正确！");
						}
						if (loanPeriod < 0 || !loanPeriod
								|| !loanPeriod.search(/\D/)) {
							msg.push("原始期限必须为大于0的数字");
						}
						if (debtMoney < 0 || !debtMoney
								|| !debtMoney.search(/\D/)) {
							msg.push("债权金额必须为大于0的数字");
						}
						if (repaymenMoney < 0 || !repaymenMoney
								|| !repaymenMoney.search(/\D/)) {
							msg.push("期限金额必须为大于0的数字");
						}
						if (debtMonthRate < 0 || !debtMonthRate
								|| !debtMonthRate.search(/\D/)) {
							msg.push("债权月利率必须为大于0的数字");
						}
						if (debtTransferredMoney < 0 || !debtTransferredMoney
								|| !debtTransferredMoney.search(/\D/)) {
							msg.push("债权转入金额必须为大于0的数字");
						}
						if (debtTransferredPeriod < 0 || !debtTransferredPeriod
								|| !debtTransferredPeriod.search(/\D/)) {
							msg.push("债权可用期限必须为大于0的数字");
						}
						if (!$("#dRepaymenDates").val()) {
							msg.push("还款日不可为空");
						}

						if ($stateParams.dId) {
							for (var i = 0; i < $scope.params.length - 2; i++) {
								if (!$scope.params[i]) {
									msg.unshift("所有债权录入数据不能为空！")
									break;
								}
							}
						} else {
							for ( var i in $scope.params) {
								if (!$scope.params[i]) {
									msg.unshift("所有债权录入数据不能为空！")
									break;
								}
							}
						}
						if (msg.length >= 1) {
							hmd.popupErrorInfo(msg[0], "error")
							return !!msg.length
						}
					}
					// 提交
					$scope.entering = function() {
						getDateVal();
						if (formInvail()) {
							return;
						}

						var str = ""
						for ( var i in $scope.params) {
							str += "&" + i + "=" + $scope.params[i]

						}
						str = str.replace(/undefined/g, "") + "&repaymenDate="
								+ $("#dRepaymenDates").val();

						console.info("str============" + str);

						PostService.entryDebet(str).success(function(response) {
							if (response.status == 1) {
								hmd.popupErrorInfo("债权录入成功！", "ok");
								$scope.params = '';
							} else {
								hmd.popupErrorInfo(response.status);
							}
						});

					}

					$scope.reset = function() {
						for ( var i in $scope.params) {
							$scope.params[i] = ""
						}
						$scope.params.repaymentStyle = 11601
						$scope.params.repaymenDate = 7
					}
				})
		.controller(
				'multiple',
				function($scope, AuthService, FileUploader, hmd) {

					var uploader = $scope.uploader = new FileUploader({
						url : '/p2p_action/creditor/upload'
					});

					uploader.filters
							.push({
								name : 'customFilter',
								fn : function(
										item /* {File|FileLikeObject} */,
										options) {
									var type = '|'
											+ item.type.slice(item.type
													.lastIndexOf('/') + 1)
											+ '|';
									var condition = ('|vnd.ms-excel|vnd.openxmlformats-officedocument.spreadsheetml.sheet|x-excel|x-msdownload|'
											.indexOf(type) !== -1)
											&& (this.queue.length < 10)
									return true;
								}
							});

					// 上传文件失败
					uploader.onWhenAddingFileFailed = function(
							item /* {File|FileLikeObject} */, filter, options) {
						hmd.popupErrorInfo('添加文件失败！', 'error');
					};

					uploader.onSuccessItem = function(fileItem, response,
							status, headers) {
						if (response.status == 1) {
							fileItem.isSuccess = true;
							fileItem.isError = false;
						} else {
							if (response.status == 101) {
								var str = '导入数据失败,第' + response.data.info
										+ '行，第' + response.data.errorCell
										+ '列数据有误';
								hmd.popupErrorInfo(str, 'error');
							} else {
								hmd.popupErrorInfo(response.status);
							}
							fileItem.isSuccess = false;
							fileItem.isError = true;
						}
					};
				})
		// 投资记录查询
		.controller(
				'investmanageCtrl',
				function($scope, $state, AuthService, PostService, hmd,
						checkParamService, jiaoyiService, $stateParams) {
					jiaoyiService.findPuerchase().success(function(response) {
						if (response.status == 1) {

							$scope.info = response.data;
							$scope.zmoney = 0;
							$scope.size = response.data.length;
							for ( var x in response.data) {
								$scope.zmoney += response.data[x].pAmount;
							}

						}
					});

					$scope.viewProductById = function(proId) {
						$scope.jyindex = $scope.info[proId];

						$scope.colseDown = true;
						$scope.recordList = false;
						$('#myModal_b').modal();

					}

					// 参数：arr =数组 prop = 属性，val = 值
					function createJson(arr, prop, val) {
						// 如果 val 被忽略
						if (typeof val === "undefined") {
							// 删除属性
							delete arr[prop];
						} else {
							// 添加 或 修改
							arr[prop] = val;
						}
					}

					$scope.chaxun = function() {
						var username = $scope.username;
						var datetimeStart = $scope.datetimeStart;
						var datetimeEnd = $scope.datetimeEnd;

						var data = {};

						if (typeof (username) != 'undefined') {

							createJson(data, "username2", username);
						}

						if (typeof (datetimeStart) != 'undefined') {

							createJson(data, "datetimeStart2", datetimeStart);
						}

						if (typeof (datetimeEnd) != 'undefined') {

							createJson(data, "datetimeEnd2", datetimeEnd);
						}

						jiaoyiService
								.chaxun(objToStr(data))
								.success(
										function(response) {
											if (response.status == 1) {

												$scope.info = response.data;
												alert(JSON
														.stringify($scope.info))
												$scope.zmoney = 0;
												$scope.size = response.data.length;
												for ( var x in response.data) {
													$scope.zmoney += response.data[x].pAmount;
												}
											}
										});
					}

					$scope.hide = function() {
						if (!$scope.recordList) {
							if ($scope.colseDown) {
								$('#myModal_b').modal('hide');
								$scope.recordList = true;
								$scope.colseDown = false;
							} else {
								$scope.recordList = true;
							}
						} else {
							$('#myModal_b').modal('hide');
						}
					}

				})
		// 每日投资统计
		.controller(
				'investstatisticsCtrl',
				function($scope, $state, AuthService, PostService, hmd,
						checkParamService, jiaoyiService, $stateParams) {

					$scope.datetimeStart = "1996-06-06";
					$scope.datetimeEnd = "2020-06-06";
					$scope.radio = "1";

					var data = {};
					jiaoyiService
							.chaxunTongJi1(objToStr(data))
							.success(
									function(response) {
										if (response.status == 1) {
											var dataxAxis = []; // 横轴数组
											var dataseries = []; // 纵轴数组
											var dataDiv5 = []; // div5的数据
											var dataDiv5legend = [];
											for ( var x in response.data) {
												dataxAxis[x] = response.data[x].pname;
												dataseries[x] = response.data[x].zmoney;
												dataDiv5[x] = {
													value : response.data[x].count,
													name : response.data[x].count
															+ '-'
															+ response.data[x].pname
												}
												dataDiv5legend[x] = response.data[x].count
														+ '-'
														+ response.data[x].pname;
											}

											// 基于准备好的DOM，初始化echarts实例
											var myChart = echarts.init(document
													.getElementById('div4'));
											// 指定图表的配置项和数据
											var option = {
												title : {
													text : '投资统计'
												},
												// 提示框组件
												tooltip : {
													// 坐标轴触发，主要用于柱状图，折线图等
													trigger : 'axis'
												},
												// 图例
												legend : {
													data : [ '历史成交总金额' ]
												},
												// 横轴
												xAxis : {
													data : dataxAxis
												},
												// 纵轴
												yAxis : {},
												// 系列列表。每个系列通过type决定自己的图表类型
												series : [ {
													name : '历史成交总金额',
													// 折线图
													type : 'line',
													data : dataseries
												} ]
											};
											// 使用刚指定的配置项和数据显示图表
											myChart.setOption(option);

											// -------------------------------

											var myChart1 = echarts
													.init(document
															.getElementById('div5'));
											option1 = {
												title : {
													text : '按产品统计',
													top : 'bottom',
													left : 'center',
													padding : 3,
													textStyle : {
														fontSize : 18,
														fontWeight : '',
														color : '#333'
													},
												},// 标题
												tooltip : {
													trigger : 'item',
													formatter : "{a} <br/>{b}: {c} ({d}%)",
												/*
												 * formatter:function(val){
												 * //让series 中的文字进行换行
												 * console.log(val);//查看val属性，可根据里边属性自定义内容
												 * var content = var['name'];
												 * return
												 * content;//返回可以含有html中标签 },
												 */// 自定义鼠标悬浮交互信息提示，鼠标放在饼状图上时触发事件
												},// 提示框，鼠标悬浮交互时的信息提示
												legend : {
													show : false,
													orient : 'vertical',
													x : 'left',
													data : dataDiv5legend
												},// 图例属性，以饼状图为例，用来说明饼状图每个扇区，data与下边series中data相匹配
												graphic : {
													type : 'text',
													left : 'center',
													top : 'center',
													style : {
														text : '投资金额统计', // 使用“+”可以使每行文字居中
														textAlign : 'center',
														font : 'italic bolder 16px cursive',
														fill : '#000',
														width : 30,
														height : 30
													}
												},// 此例饼状图为圆环中心文字显示属性，这是一个原生图形元素组件，功能很多
												series : [
														{
															name : '投资金额统计',// tooltip提示框中显示内容
															type : 'pie',// 图形类型，如饼状图，柱状图等
															radius : [ '35%',
																	'65%' ],// 饼图的半径，数组的第一项是内半径，第二项是外半径。支持百分比，本例设置成环形图。具体可以看文档或改变其值试一试
															// roseType:'area',是否显示成南丁格尔图，默认false
															itemStyle : {
																normal : {
																	label : {
																		show : true,
																		textStyle : {
																			color : '#3c4858',
																			fontSize : "18"
																		},
																		formatter : function(
																				val) { // 让series
																			// 中的文字进行换行
																			return val.name
																					.split(
																							"-")
																					.join(
																							"\n");
																		}
																	},// 饼图图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等。可以与itemStyle属性同级，具体看文档
																	labelLine : {
																		show : true,
																		lineStyle : {
																			color : '#3c4858'
																		}
																	}
																// 线条颜色
																},// 基本样式
																emphasis : {
																	shadowBlur : 10,
																	shadowOffsetX : 0,
																	shadowColor : 'rgba(0, 0, 0, 0.5)',// 鼠标放在区域边框颜色
																	textColor : '#000'
																}
															// 鼠标放在各个区域的样式
															},
															data : dataDiv5,// 数据，数据中其他属性，查阅文档
															color : [
																	'#51CEC6',
																	'#FFB703',
																	'#5FA0FA' ],// 各个区域颜色
														},// 数组中一个{}元素，一个图，以此可以做出环形图
												],// 系列列表
											};
											myChart1.setOption(option1);
										} else {
											hmd.popupErrorInfo(response.status);
										}
									});

					$scope.cx = function() {

						var radio = $scope.radio;
						var datetimeStart = $scope.datetimeStart;
						var datetimeEnd = $scope.datetimeEnd;
						var data = {
							datetimeStartParam : datetimeStart,
							datetimeEndParam : datetimeEnd,
							radioParam : radio
						};

						jiaoyiService
								.chaxunTongJi2(objToStr(data))
								.success(
										function(response) {
											if (response.status == 1) {
												var dataxAxis = []; // 横轴数组
												var dataseries = []; // 纵轴数组
												var datalegend = [];// 图例
												var dataDiv5 = []; // div5的数据
												var title = '';
												var dataDiv5legend = [];
												if (radio == "1") {
													datalegend[0] = datetimeStart
															+ ' 至   '
															+ datetimeEnd
															+ '  内的成交总金额';
													title = '按这段日期范围内统计';
												} else if (radio == "2") {
													datalegend[0] = '成交总笔数';
													title = '按产品成交笔数统计';
												}

												for ( var x in response.data) {
													dataxAxis[x] = response.data[x].pname;
													dataseries[x] = response.data[x].zmoney;
													dataDiv5[x] = {
														value : response.data[x].count,
														name : response.data[x].count
																+ '-'
																+ response.data[x].pname
													}
													dataDiv5legend[x] = response.data[x].count
															+ '-'
															+ response.data[x].pname;
												}

												// 基于准备好的DOM，初始化echarts实例
												var myChart = echarts
														.init(document
																.getElementById('div4'));
												// 指定图表的配置项和数据
												var option = {
													title : {
														text : '投资统计'
													},
													// 提示框组件
													tooltip : {
														// 坐标轴触发，主要用于柱状图，折线图等
														trigger : 'axis'
													},
													// 图例
													legend : {
														data : datalegend
													},
													// 横轴
													xAxis : {
														data : dataxAxis
													},
													// 纵轴
													yAxis : {},
													// 系列列表。每个系列通过type决定自己的图表类型
													series : [ {
														name : datalegend[0],
														// 折线图
														type : 'line',
														data : dataseries
													} ]
												};
												// 使用刚指定的配置项和数据显示图表
												myChart.setOption(option);

												// ------------------------------

												// -------------------------------

												var myChart1 = echarts
														.init(document
																.getElementById('div5'));
												option1 = {
													title : {
														text : title,
														top : 'bottom',
														left : 'center',
														padding : 3,
														textStyle : {
															fontSize : 18,
															fontWeight : '',
															color : '#333'
														},
													},// 标题
													tooltip : {
														trigger : 'item',
														formatter : "{a} <br/>{b}: {c} ({d}%)",
													/*
													 * formatter:function(val){
													 * //让series 中的文字进行换行
													 * console.log(val);//查看val属性，可根据里边属性自定义内容
													 * var content =
													 * var['name']; return
													 * content;//返回可以含有html中标签 },
													 */// 自定义鼠标悬浮交互信息提示，鼠标放在饼状图上时触发事件
													},// 提示框，鼠标悬浮交互时的信息提示
													legend : {
														show : false,
														orient : 'vertical',
														x : 'left',
														data : dataDiv5legend
													},// 图例属性，以饼状图为例，用来说明饼状图每个扇区，data与下边series中data相匹配
													graphic : {
														type : 'text',
														left : 'center',
														top : 'center',
														style : {
															text : '投资金额统计', // 使用“+”可以使每行文字居中
															textAlign : 'center',
															font : 'italic bolder 16px cursive',
															fill : '#000',
															width : 30,
															height : 30
														}
													},// 此例饼状图为圆环中心文字显示属性，这是一个原生图形元素组件，功能很多
													series : [
															{
																name : '投资金额统计',// tooltip提示框中显示内容
																type : 'pie',// 图形类型，如饼状图，柱状图等
																radius : [
																		'35%',
																		'65%' ],// 饼图的半径，数组的第一项是内半径，第二项是外半径。支持百分比，本例设置成环形图。具体可以看文档或改变其值试一试
																// roseType:'area',是否显示成南丁格尔图，默认false
																itemStyle : {
																	normal : {
																		label : {
																			show : true,
																			textStyle : {
																				color : '#3c4858',
																				fontSize : "18"
																			},
																			formatter : function(
																					val) { // 让series
																				// 中的文字进行换行
																				return val.name
																						.split(
																								"-")
																						.join(
																								"\n");
																			}
																		},// 饼图图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等。可以与itemStyle属性同级，具体看文档
																		labelLine : {
																			show : true,
																			lineStyle : {
																				color : '#3c4858'
																			}
																		}
																	// 线条颜色
																	},// 基本样式
																	emphasis : {
																		shadowBlur : 10,
																		shadowOffsetX : 0,
																		shadowColor : 'rgba(0, 0, 0, 0.5)',// 鼠标放在区域边框颜色
																		textColor : '#000'
																	}
																// 鼠标放在各个区域的样式
																},
																data : dataDiv5,// 数据，数据中其他属性，查阅文档
																color : [
																		'#51CEC6',
																		'#FFB703',
																		'#5FA0FA' ],// 各个区域颜色
															},// 数组中一个{}元素，一个图，以此可以做出环形图
													],// 系列列表
												};
												myChart1.setOption(option1);
											} else {
												hmd
														.popupErrorInfo(response.status);
											}

										})

					}

				})
		// 角色
		.controller(
				'jueseCtrl',
				function($scope, $state, AuthService, PostService,
						AdminService, hmd, checkParamService, jiaoyiService,
						$stateParams) {
					AdminService.findAllAdmin().success(function(response) {
						if (response.status == 1) {
							$scope.ad = response.data;
						} else {
							hmd.popupErrorInfo(response.status);
						}
					})

					$scope.sz = function(aId, aname) {
						$scope.admin_Id = aId;
						$scope.admin_Name = aname;
						$("#userTitle").show()

						AdminService
								.findAlljuese()
								.success(
										function(response) {
											if (response.status == 1) {

												var setting = {
													check : {
														enable : true
													},
													data : {
														simpleData : {
															enable : true
														}
													}
												};

												var zNodes = [ {
													id : 0,
													name : "管理员角色",
													open : true,
													nocheck : true
												} ];

												for ( var x in response.data) {

													var node = {
														id : response.data[x].id,
														pId : 0,
														name : response.data[x].rolename
													}

													zNodes.push(node)
												}

												$.fn.zTree.init($("#treeDemo"),
														setting, zNodes);

												setCheck();

											} else {
												hmd
														.popupErrorInfo(response.status);
											}
										})

					}

					$scope.saveRole = function(admin_Id) {

						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var nodes = treeObj.getCheckedNodes(true);
						var nodesIds = "";
						for ( var node in nodes) {

							nodesIds += nodes[node].id + ",";
						}

						var data = {
							adminId : admin_Id,
							jueses_Ids : nodesIds
						};

						AdminService
								.updateRole(objToStr(data))
								.success(
										function(response) {
											if (response.status == 1) {
												alert("saveOk")
												$("#userTitle").hide()
											} else {
												hmd
														.popupErrorInfo(response.status);
											}
										})

					}

					$scope.quxiao = function() {
						$("#userTitle").hide()
					}

				})
		// 权限
		.controller(
				'quanxianCtrl',
				function($scope, $state, AuthService, PostService,
						AdminService, hmd, checkParamService, jiaoyiService,
						$stateParams) {
					AdminService.findAlljuese().success(function(response) {
						if (response.status == 1) {
							$scope.js = response.data;
						} else {
							hmd.popupErrorInfo(response.status);
						}
					})

					$scope.sz = function(roleId, roleName) {
						$scope.role_Id = roleId;
						$scope.role_name = roleName;
						$("#userTitle").show()

						AdminService
								.findAllquanxian()
								.success(
										function(response) {
											if (response.status == 1) {
												var setting = {
													check : {
														enable : true
													},
													data : {
														simpleData : {
															enable : true
														}
													}
												};

												var zNodes = [];

												for ( var x in response.data) {

													var node = {
														id : response.data[x].id,
														pId : response.data[x].parentId,
														name : response.data[x].name
													}

													zNodes.push(node)
												}

												$.fn.zTree.init($("#treeDemo"),
														setting, zNodes);

												setQxCheck();

											} else {
												hmd
														.popupErrorInfo(response.status);
											}
										})
					}
					
					
					
					$scope.saveRole = function(role_Id) {

						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var nodes = treeObj.getCheckedNodes(true);
						var nodesIds = "";
						for ( var node in nodes) {

							nodesIds += nodes[node].id + ",";
						}

						var data = {
							roleId : role_Id,
							quanxian_Ids : nodesIds
						};

						AdminService
								.updatePrivilege(objToStr(data))
								.success(
										function(response) {
											if (response.status == 1) {
												alert("saveOk")
												$("#userTitle").hide()

											} else {
												hmd
														.popupErrorInfo(response.status);
											}
										})

					}

					$scope.quxiao = function() {
						$("#userTitle").hide()
					}

				})
