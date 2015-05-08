app.controller('listDetailCtrl', ['$scope', '$state', '$stateParams', '$http', '$cookieStore',
                                  function($scope, $state, $stateParams, $http, $cookieStore) {
	$http.get('/store/phone/' + $stateParams.id)
		.success(function(data, status, headers, config) {
			$scope.phone = data;
		})
		.error(function(data, status, headers, config) {
			console.log('Error find $id Phone');
		});
		
	$scope.addCart = function(price) {
		var check = true;
		var cart = [];
		for(phone in $cookieStore.get('phone_cart')) {
			cart.push($cookieStore.get('phone_cart')[phone]);
			if($cookieStore.get('phone_cart')[phone].id === $stateParams.id) {
				alert('San pham nay da co trong gio cua ban');
				check = false;
			}
		}
		if(check) {
			cart.push({id: $stateParams.id, quantity: 1});
			$cookieStore.put('phone_cart', cart);
			$state.go('cart.cart', {});
		}
	}
}]);