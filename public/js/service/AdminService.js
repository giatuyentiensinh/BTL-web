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

	var deletePhone = function(id) {
		return $http({
			method: 'DELETE',
			url: '/store/phone/' + id,
			headers: {
				xsrfHeaderName: 'sercurity'
			}
		})
	};

	var updatePhone = function(id, phone) {
		// console.log('Update phone on service');
		// console.log(phone.price);
		// console.log(id);		
		return $http({
			method: 'PUT',
			url: '/store/phone/' + id,
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				phone: phone
			}
		})
	};

	var createPhone = function(phone) {
		// console.log('Create phone on service');
		// console.log(phone);
		// console.log('end');
		return $http({
			method: 'POST',
			url: '/store/phone',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				phone: phone
			}
		});
	};

	var bill = function() {
		return $http({
			method: 'GET',
			url: '/store/readBill',
			headers: {
				xsrfHeaderName: 'sercurity'
			}
		});
	};

	var readComment = function() {
		return $http({
			method: 'GET',
			url: '/store/readComment',
			headers: {
				xsrfHeaderName: 'sercurity'
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
		deletePhone: function(id) {
			return deletePhone(id);
		},
		updatePhone: function(id, phone) {
			return updatePhone(id, phone);
		},
		createPhone: function(phone) {
			return createPhone(phone);
		},
		readBill: function() {
			return bill();
		},
		readComment: function() {
			return readComment();
		}
	}

}]);