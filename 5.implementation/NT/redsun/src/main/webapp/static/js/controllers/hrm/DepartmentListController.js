/**
 * Controller for Resources
 */

app.controller('DepartmentListController', [
		'$scope',
		'$rootScope',
		'$http',
		'$log',
		'$window',
		'HRListService',
		'$templateRequest',
		'$mdDialog',
		'DepartmentService',
		function($scope, $rootScope, $http, $log, $window, HRListService,
				$templateRequest, $mdDialog, DepartmentService) {
			$rootScope.configTree = {
				container : "#custom-colored",
				nodeAlign : "BOTTOM",
				connectors : {
					type : 'step'
				},
				node : {
					HTMLclass : 'department'
				}
			};
			$scope.pageLoad = function() {

				DepartmentService.getDepartment().success(
						function(response, status, headers, config) {
							response = angular.fromJson(response);
							if (response && response.status === 1
									&& response.result.listDepartments && response.result.listDepartments.length > 0) {
								var listDepartments = response.result.listDepartments;
									
								var dic = {};
								dic["p" + listDepartments[0].id] = {
										HTMLclass : 'blue',
										text : {
											name : 'Department',
											title : listDepartments[0].name,
											manager: listDepartments[0].chiefName
										},
										image : "/redsun"+ listDepartments[0].img + "*" + listDepartments[0].id
									}
								var chart_config = [$rootScope.configTree, dic["p" + listDepartments[0].id]];
								for(var i =1; i < listDepartments.length;i++ ){
									dic["p" + listDepartments[i].id] = {
											parent : dic["p" + listDepartments[i].parentId],
											HTMLclass : 'blue',
											text : {
												name : "Department",
												title : listDepartments[i].name,
												manager: listDepartments[i].chiefName
											},
											image : "/redsun"+ listDepartments[0].img + "*" + listDepartments[i].id
										}
									chart_config.push(dic["p" + listDepartments[i].id]);
								}
								new Treant(chart_config);
								
								
							} else {
								$window.alert('failed');
							}
						})
				// error.
				.error(function(response, status, headers, config) {
					$window.alert('error');
				});

//				var config = {
//					container : "#custom-colored",
//
//					nodeAlign : "BOTTOM",
//
//					connectors : {
//						type : 'step'
//					},
//					node : {
//						HTMLclass : 'nodeExample1'
//					}
//				}, ceo = {
//					text : {
//						name : "Mark Hill",
//						title : "Chief executive officer",
//						contact : "Tel: 01 213 123 134",
//					},
//					image : "/redsun/static/images/user.jpg*1"
//				},
//
//				cto = {
//					parent : ceo,
//					HTMLclass : 'light-gray',
//					text : {
//						name : "Joe Linux",
//						title : "Chief Technology Officer",
//					},
//					image : "/redsun/static/images/user.jpg*2"
//				}, cbo = {
//					parent : ceo,
//					childrenDropLevel : 2,
//					HTMLclass : 'blue',
//					text : {
//						name : "Linda May",
//						title : "Chief Business Officer",
//					},
//					image : "/redsun/static/images/user.jpg*3"
//				}, cdo = {
//					parent : ceo,
//					HTMLclass : 'gray',
//					text : {
//						name : "John Green",
//						title : "Chief accounting officer",
//						contact : "Tel: 01 213 123 134",
//					},
//					image : "/redsun/static/images/user.jpg*4"
//				}, cdo2 = {
//					parent : ceo,
//					HTMLclass : 'gray',
//					text : {
//						name : "John Green 2",
//						title : "Chief accounting officer",
//						contact : "Tel: 01 213 123 134",
//					},
//					image : "/redsun/static/images/user.jpg*5"
//				}, cio = {
//					parent : cto,
//					HTMLclass : 'light-gray',
//					text : {
//						name : "Ron Blomquist",
//						title : "Chief Information Security Officer"
//					},
//					image : "/redsun/static/images/user.jpg*6"
//				}, ciso = {
//					parent : cto,
//					HTMLclass : 'light-gray',
//					text : {
//						name : "Michael Rubin",
//						title : "Chief Innovation Officer",
//						contact : "we@aregreat.com"
//					},
//					addNew : "add_new",
//					image : "/redsun/static/images/user.jpg*7"
//				}, cio2 = {
//					parent : cdo,
//					HTMLclass : 'gray',
//					text : {
//						name : "Erica Reel",
//						title : "Chief Customer Officer",
//						contact : "77777777"
//
//					},
//					link : {
//						href : "http://www.google.com"
//					},
//					image : "/redsun/static/images/user.jpg*8"
//				}, ciso2 = {
//					parent : cbo,
//					HTMLclass : 'blue',
//					text : {
//						name : "Alice Lopez",
//						title : "Chief Communications Officer"
//					},
//					image : "/redsun/static/images/user.jpg*9"
//				}, ciso3 = {
//					parent : cbo,
//					HTMLclass : 'blue',
//					text : {
//						name : "Mary Johnson",
//						title : "Chief Brand Officer"
//					},
//					image : "/redsun/static/images/user.jpg*10"
//				}, ciso4 = {
//					parent : cbo,
//					HTMLclass : 'blue',
//					text : {
//						name : "Kirk Douglas",
//						title : "Chief Business Development Officer"
//					},
//					image : "/redsun/static/images/user.jpg*11"
//				},
//
//				chart_config = [ config, ceo, cto, cbo, cdo, cdo2, cio, ciso,
//						cio2, ciso2, ciso3, ciso4 ];
//				$rootScope.chart_config = chart_config;
//				new Treant(chart_config);
			}
			$scope.pageLoad();

			$scope.addNode = function() {

				$templateRequest("/redsun/static/partials/addDepartment.html")
						.then(
								function(html) {
									//$compile(html)($scope);
									var saveDepartment = $mdDialog.confirm({
										template : html,
										clickOutsideToClose : false,
										scope : $scope,
										preserveScope : true, // do not forget this if use parent scope
										controller : function DialogController(
												$scope, $mdDialog) {
											$scope.closeDialog = function() {
												$mdDialog.hide();
											}
										}

									});
									$mdDialog.show(saveDepartment);

								});

				var config = {
					container : "#custom-colored",

					nodeAlign : "BOTTOM",

					connectors : {
						type : 'step'
					},
					node : {
						HTMLclass : 'department'
					}
				}

				var dic = {};
				var master = {
					HTMLclass : 'blue',
					text : {
						name : "Thuong",
						title : "Chief Business Development Officer"
					},
					image : "/redsun/static/images/user.jpg*0"
				}
				var chart_config = [ config, master ];
				var j = 4;
				var k = 10;
				var m = 15;
				for (var i = 1; i <= 20; i++) {
					if (i <= 5) {
						dic["p" + i] = {
							parent : master,
							HTMLclass : 'blue',
							text : {
								name : "level 2",
								title : "Chief Business Development Officer"
							},
							image : "/redsun/static/images/user.jpg*" + i
						}
					} else if (i > 5 && i <= 10) {
						dic["p" + i] = {
							parent : dic["p" + (j--)],
							HTMLclass : 'blue',
							text : {
								name : "level 3",
								title : "Chief Business Development Officer"
							},
							image : "/redsun/static/images/user.jpg*" + i
						}
					} else if (i > 10 && i <= 15) {
						dic["p" + i] = {
							parent : dic["p" + (k--)],
							HTMLclass : 'blue',
							text : {
								name : "level 4",
								title : "Chief Business Development Officer"
							},
							image : "/redsun/static/images/user.jpg*" + i
						}
					} else if (i > 15 && i <= 20) {
						dic["p" + i] = {
							parent : dic["p" + (m--)],
							HTMLclass : 'blue',
							text : {
								name : "level 5",
								title : "Chief Business Development Officer"
							},
							image : "/redsun/static/images/user.jpg*" + i
						}
					}
					chart_config.push(dic["p" + i]);
				}

				new Treant(chart_config);
			}
			
			$scope.exportOrgPDF = function(fileName, canvasId){
				createPDFbyLand(fileName, canvasId);
			}
		} ]);