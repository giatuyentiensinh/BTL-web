app.controller('searchCtrl', ['$scope', '$modal', 
	function($scope, $modal) {
		$scope.search = function() {
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'searchPhone.html',
				controller: 'SearchInstanceCtrl',
				size: 'lg'
			});

			modalInstance.result.then(function(result) {
				// console.log(result);
			}, function() {
				console.log('dismiss');
			});
		}
}]);

app.controller('SearchInstanceCtrl', ['$scope', '$modalInstance', '$state', 'PhoneService',
	function($scope, $modalInstance, $state, PhoneService) {
		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};

		PhoneService.resultPage(0, 0)
			.success(function(data, status, headers, config) {
				// console.log('Length:');
				// console.log(data);
				PhoneService.resultPage(0, data)
					.success(function(result) {
						// console.log(result);
						$scope.phones = result;
					})
					.error(function(resp) {
						console.log('error');
					});
			})
			.error(function(data, status, headers, config) {
				console.log('Server error');
			});

		$scope.rederDetail = function(product_id) {
			// console.log(product_id);
			$state.go('product.detail', {id: product_id});
			$modalInstance.close(product_id);
		}

	}]);