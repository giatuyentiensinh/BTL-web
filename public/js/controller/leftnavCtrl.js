app.controller('leftnavCtrl', ['$scope', '$http', '$state', 'PhoneService', 
						function($scope, $http, $state, PhoneService) {



	PhoneService.resultCategory()
		.success(function(data, status, headers, config) {
			$scope.categories = data;
		})
		.error(function(data, status, header, config) {
			console.log('Error not load category');
		});

	$scope.rederDetail = function(product_id) {
		$state.go('product.detail', {id: product_id});
	}

	PhoneService.resultCost(2000000, 0)
		.success(function(data, status, header, config) {
			$scope.phone1 = data;
			// console.log('phone1');
			// console.log(data);
		})
		.error(function(data, status, header, config) {
			console.log('error load less than phone');
		});

	PhoneService.resultCost(2000000, 5000000)
		.success(function(data, status, header, config) {
			$scope.phone2 = data;
			// console.log('phone2');
			// console.log(data);
		})
		.error(function(data, status, header, config) {
			console.log('error load less than and greater than phone');
		})

	PhoneService.resultCost(0, 5000000)
		.success(function(data, status, header, config) {
			$scope.phone3 = data;
			// console.log('phone3');
			// console.log($scope.phone3);
		})
		.error(function(data, status, header, config) {
			console.log('error load greater than phone');
		})

}]);