app.factory('PhoneService', ['$http', function($http) {
	var doRequest = function(index, number) {
		return $http({
			method: 'GET',
			url: '/store/phone',
			headers: {
				xsrfHeaderName: 'sercurity',
				offset: index,
				count: number
			}
		});
		// return $http.get('/store/phone', {offset: index, count: number});
	};

	var findPhoneId = function(id) {
		return $http({
			method: 'GET',
			url: '/store/phone/' + id,
			headers: {
				xsrfHeaderName: 'sercurity'
			}
		});
	};

	var readCart = function() {
		return $http({
			method: 'GET',
			url: '/store/cart',
			headers: {
				xsrfHeaderName: 'sercurity'
			}
		});
	};

	var buyPhone = function(info, item ) {
		// console.log("---------Service---------");
		// console.log("info");
		// console.log(info);
		// console.log("item");
		// console.log(item);
		return $http({
			method: 'POST',
			url: '/buyphone',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				info: info,
				item: item
			}
		});
	};

	var comment = function(content) {
		// console.log("---------Service---------");
		// console.log(content);
		return $http({
			method: 'POST',
			url: '/comments',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				comment : content
			}
		});
	};

	return {
		resultPage: function(index, number) {
			return doRequest(index, number);
		},
		returnCount: function() {
			return doRequest(0, 0);
		},
		phoneId: function(id) {
			return findPhoneId(id);
		},
		readCart: function() {
			return readCart();
		},
		buy: function(infoUser, item) {
			return buyPhone(infoUser, item);
		},
		comment: function(content) {
			return comment(content);
		}
	};
}]);