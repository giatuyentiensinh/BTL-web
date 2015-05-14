app.controller('productCtrl', ['$scope', '$state', '$cookieStore', 'PhoneService',
                               function($scope, $state, $cookieStore, PhoneService) {
	
	$scope.itemPerPage = 6;
	$scope.currentPage = 1;

	PhoneService.resultPage(0, 0)
		.success(function(data, status, headers, config) {
			$scope.totalItems = data;			
		})
		.error(function(data, status, headers, config) {
			console.log("Server error");
		});

	// $scope.maxSize = $scope.totalItems / $scope.itemPerPage;
	$scope.maxSize = 3;

	PhoneService.resultPage(0, $scope.itemPerPage)
		.success(function(data, status, headers, config) {
			$scope.data = data;
			// console.log(data);
		})
		.error(function(data, status, headers, config) {
			console.log("Server error");
		});
	
	$scope.rederDetail = function(product_id) {
		// console.log(product_id);
		
		$state.go('product.detail', {id: product_id});
	}
	
	$scope.addCart = function(product_id) {
		var check = true;		
		var cookies = document.cookie.split('; ');

		// console.log(document.cookie);

		for(var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i].split('=');
			
			if(cookie[0] === ('cart_' + product_id)) {
				alert('San pham nay da co trong gio cua ban');
				check = false;
			}
		}
		if(check) {
			$cookieStore.put('cart_' + product_id, 1);
			$cookieStore.put('reload', true);
			$state.go('cart.cart', {});
		}
	}

	$scope.changed = function() {
		PhoneService.resultPage(($scope.currentPage - 1), $scope.itemPerPage)
			.success(function(data, status, headers, config) {
				$scope.data = data;
			}).error(function(data, status, headers, config) {
				console.log("server error");
			});
	}
}]);