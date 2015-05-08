app.controller('leftnavCtrl', ['$scope', '$http', function($scope, $http) {
	$http.get('/store/category')
		.success(function(data, status, headers, config) {
			$scope.categories = data;
		})
		.error(function(data, status, header, config) {
			console.log('Error not load category');
		});
}]);