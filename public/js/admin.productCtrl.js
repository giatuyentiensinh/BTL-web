admin.controller('AdminProductCtrl', ['$scope', '$http',
	function($scope, $http) {
		$http.get('/store/phone', {cache: true})
			.success(function(data, status, headers, config) {
				$scope.phones = data;
			})
			.error(function(data, status, headers, config) {
				console.log('error');
			});
	}]);