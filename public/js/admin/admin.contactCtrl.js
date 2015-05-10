admin.controller('AdminContactCtrl', ['$scope', 'AdminService',
	function($scope, AdminService) {
		AdminService.resultPage(0, 0)
			.success(function(data, status, headers, config) {
				$scope.count = data;
			})
			.error(function(data, status, headers, config) {
				console.log("Error to server");
				console.log(data);
			})
	}]);