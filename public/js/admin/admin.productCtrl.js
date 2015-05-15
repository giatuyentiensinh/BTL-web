admin.controller('AdminProductCtrl', ['$scope', '$modal','AdminService',	
	function($scope, $modal, AdminService) {

		$scope.itemPerPage = 6;
		$scope.currentPage = 1;

		AdminService.resultPage(0, 0)
			.success(function(data, status, headers, config) {
				$scope.totalItems = data;			
			})
			.error(function(data, status, headers, config) {
				if(data=='not login') {
					console.log("Hacker");
					AdminService.redirect('login');
				} else {
					console.log("Server error");
				}
			});

		AdminService.resultPage(0, $scope.itemPerPage)
			.success(function(data, status, headers, config) {
				$scope.phones = data;
				console.log("Load Success");
			})
			.error(function(data, status, headers, config) {
				if(data=='not login') {
					console.log("Hacker");
					AdminService.redirect('login');
				} else {
					console.log("Error to Server");
					console.log(data);
				}

			});

		$scope.changePagination = function() {
			AdminService.resultPage(($scope.currentPage - 1), $scope.itemPerPage)
				.success(function(data, status, headers, config) {
					$scope.phones = data;
				}).error(function(data, status, headers, config) {
					if(data=='not login') {
						console.log("Hacker");
						AdminService.redirect('login');
					} else {
						console.log("server error");
					}
				});
		}

		$scope.deletePhone = function(id) {
			AdminService.deletePhone(id)
				.success(function(data, status, headers, config) {
					console.log("Delete success");
				}).error(function(data, status, headers, config) {
					if(data=='not login') {
						console.log("Hacker");
						AdminService.redirect('login');
					} else {
						console.log("server error");
					}
				});
			$state.go($state.current, {}, {reload: true});
		}

		$scope.changePhone = function(id, phone) {
			// console.log(phone);
			// $scope.name = phone.name;
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'changePhoneId.html',
				controller: 'MotalInstanceCtrl',
				size: 'lg',
				resolve: {
					phone: function() {
						return phone;
					},
					title: function() {
						return 'Thay đổi sản phẩm';
					}
				}
			});
			modalInstance.result.then(function(phoneBeChange) {
				// console.log('after');
				// console.log(phoneBeChange);
				AdminService.updatePhone(id, phoneBeChange)
					.success(function(data, status, headers, config) {
						console.log(data);
						$state.go($state.current, {}, {reload: true});
					}).error(function(data, status, headers, config) {
						if(data=='not login') {
							console.log("Hacker");
							AdminService.redirect('login');
						} else {
							console.log("server error");
						}
					});
				// $state.go($state.current, {}, {reload: true});
			}, function() {
				console.log('dismiss');
			});
		}

		$scope.addPhone = function() {
			var phone = {
				id: '', 
				name: '', 
				size: '', 
				weight: '', 
				price: 0, 
				image: '', 
				provider: '', 
				configuration: {
					OS: '',
					display: '',
					cpu: '',
					ram: '',
					pin: '',
					camera: {
						front: '',
						back: ''
					},
					network: {
						wifi: '',
						bluetooth: ''
					}
				}
			};

			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'changePhoneId.html',
				controller: 'MotalInstanceCtrl',
				size: 'lg',
				resolve: {
					phone: function() {
						return phone;
					},
					title: function() {
						return 'Tạo mới sản phẩm';
					}
				}
			});

			modalInstance.result.then(function(phoneBeCreate) {
				console.log('after');
				console.log(phoneBeCreate);
				console.log('---------------------------');
				AdminService.createPhone(phoneBeCreate)
					.success(function(data, status, headers, config) {
						console.log('Created...');
						console.log(data);
						$state.go($state.current, {}, {reload: true});
					}).error(function(data, status, headers, config) {
						if(data=='not login') {
							console.log("Hacker");
							AdminService.redirect('login');
						} else {
							console.log("server error");
						}
					});
				// $state.go($state.current, {}, {reload: true});
			}, function() {
				console.log('dismiss');
			});
		}
}]);

admin.controller('MotalInstanceCtrl', ['$scope', '$state', '$modalInstance', 'phone', 'title',
	function($scope, $state, $modalInstance, phone, title) {
		$scope.title = title;
		// mapping phone modal and request
		// $scope.id = phone.id;
		$scope.name = phone.name;
		$scope.size = phone.size;
		$scope.weight = phone.weight;
		$scope.price = phone.price;
		$scope.image = phone.image;
		$scope.provider = phone.provider;
		$scope.os = phone.configuration.OS;
		$scope.display = phone.configuration.display;
		$scope.cpu = phone.configuration.cpu;
		$scope.ram = phone.configuration.ram;
		$scope.pin = phone.configuration.pin;
		$scope.front = phone.configuration.camera.front;
		$scope.back = phone.configuration.camera.back;
		$scope.wifi = phone.configuration.network.wifi;
		$scope.bluetooth = phone.configuration.network.bluetooth;

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};

		$scope.ok = function() {
			var phone = {
				id: $scope.id,
				name: $scope.name,
				size: $scope.size,
				weight: $scope.weight,
				price: $scope.price.toString(),
				image: $scope.image,
				provider: $scope.provider,
				configuration: {
					OS: $scope.os,
					display: $scope.display,
					cpu: $scope.cpu,
					ram: $scope.ram,
					pin: $scope.pin,
					camera: {
						front: $scope.front,
						back: $scope.back
					},
					network: {
						wifi: $scope.wifi,
						bluetooth: $scope.bluetooth
					}
				}
			};
			// console.log('price: ' + $scope.price);
			// console.log('Befor');
			// console.log(phone);
			$modalInstance.close(phone);
		}
}]);