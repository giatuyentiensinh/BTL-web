admin.controller('AdminAccountCtrl', ['$scope', 'AdminService',
	function($scope, AdminService) {

		AdminService.checkAdmin()
			.success(function(data, status, headers, config) {
				console.log('success: ' + data);
			})
			.error(function(data, status, headers, config) {
				AdminService.redirect('login');
			});

		$scope.logout = function() {
			console.log('logout');
			AdminService.logout()
				.success(function(data, status, headers, config) {
					AdminService.redirect('login');
				})
				.error(function(data, status, headers, config) {
					console.log('Server error');
				});
		}

		$scope.changeName = function(name, pass, newName) {
			// console.log(name);
			// console.log(newName);
			// console.log(pass);
			AdminService.changeName(name, pass, newName)
				.success(function(data, status, headers, config){
					$scope.messageName = 'Thay đổi tên thành công';
					$scope.flugClass = 'alert alert-success fade in';
				})
				.error(function(data, status, headers, config) {
					console.log(data);
					if(data === 'incorrect') {
						console.log('not sure');
						$scope.messageName = 'Tài khoản của bạn không chính xác';
						$scope.flugClass = 'alert alert-danger fade in';
					} else {
						$scope.messageName = 'Đã có lỗi xảy ra';
						$scope.flugClass = 'alert alert-danger fade in';
					}
				});
		}		

		$scope.changePass = function(name, pass, newPass) {
			console.log(name);
			console.log(newPass);
			// console.log(pass);
			AdminService.changePass(name, pass, newPass)
				.success(function(data, status, headers, config){
					$scope.messagePass = 'Thay đổi mật khẩu thành công';
					$scope.flugClass = 'alert alert-success fade in';
				})
				.error(function(data, status, headers, config) {
					console.log(data);
					if(data === 'incorrect') {
						console.log('not sure');
						$scope.messagePass = 'Tài khoản của bạn không chính xác';
						$scope.flugClass = 'alert alert-danger fade in';
					} else {
						$scope.messagePass = 'Đã có lỗi xảy ra';
						$scope.flugClass = 'alert alert-danger fade in';
					}
				});
		}	

		$scope.checkConfirm = function() {
			if($scope.confirmpass === $scope.newpass)
				$scope.confirm = false;
			else
				$scope.confirm = true;
		}

}]);