app.controller('listDetailCtrl', ['$scope', '$state', '$stateParams', 'PhoneService', '$cookieStore',
                                  function($scope, $state, $stateParams, PhoneService, $cookieStore) {
		
	PhoneService.phoneId($stateParams.id) 
		.success(function(data, status, headers, config) {
				$scope.phone = data;
			})
		.error(function(data, status, headers, config) {
			console.log('Error find $id Phone');
			console.log(data);
		});

	$scope.addCart = function() {
		var check = true;		
		var cookies = document.cookie.split('; ');

		// console.log(document.cookie);

		for(var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i].split('=');
			
			if(cookie[0] === ('cart_' + $stateParams.id)) {
				alert('San pham nay da co trong gio cua ban');
				check = false;
			}
		}
		if(check) {
			$cookieStore.put('cart_' + $stateParams.id, 1);
			$cookieStore.put('reload', true);
			$state.go('cart.cart', {});
		}
	}
}]);