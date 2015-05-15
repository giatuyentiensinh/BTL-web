admin.controller('LoginCtrl', ['$scope', 'AdminService', function($scope, AdminService) {

	$scope.login = function() {
		$scope.messageServer = '';

		AdminService.login($scope.username, $scope.password)
			.success(function(data, status, headers, config) {
				// console.log(data);
				if(data) {
					// console.log('check');
					// $state.go('product');
					AdminService.redirect('product');
				}
			})
			.error(function(data, status, headers, config) {
				console.log(data);
				if(data == 'Check account again')
					$scope.messageServer = 'Kiểm tra lại tài khoản của bạn';
				else
					$scope.messageServer = 'Đã có lỗi sảy ra';
			});

	}
}]);