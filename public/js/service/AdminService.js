admin.factory('AdminService', ['$http', function($http) {

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

	var bill = function(id) {
		return $http({
			method: 'GET',
			url: '/store/readBill',
			headers: {
				xsrfHeaderName: 'sercurity'
			}
		});
	}


	return {
		resultPage: function(index, number) {
			return doRequest(index, number);
		},
		returnCount: function() {
			return doRequest(0, 0);
		},
		phoneId: function(id) {
			return findPhoneId(id);
		}
	}

}]);