admin.controller('AdminProductCtrl', ['$scope', 'AdminService',	
	function($scope, AdminService) {

		$scope.itemPerPage = 2;
		$scope.currentPage = 1;

		AdminService.resultPage(0, 0)
			.success(function(data, status, headers, config) {
				$scope.totalItems = data;			
			})
			.error(function(data, status, headers, config) {
				console.log("Server error");
			});

		AdminService.resultPage(0, $scope.itemPerPage)
			.success(function(data, status, headers, config) {
				$scope.phones = data;
				console.log("Success");
			})
			.error(function(data, status, headers, config) {
				console.log("Error to Server");
				console.log(data);

			});



	$scope.changed = function() {
		AdminService.resultPage(($scope.currentPage - 1), $scope.itemPerPage)
			.success(function(data, status, headers, config) {
				$scope.phones = data;
			}).error(function(data, status, headers, config) {
				console.log("server error");
			});
	}
	}]);