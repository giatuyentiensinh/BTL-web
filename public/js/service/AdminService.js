admin.factory('AdminService', ['$http', '$state', function($http, $state) {

	var login = function(username, password) {
		return $http({
			method: 'POST',
			url: '/login',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				username: username,
				password: password
			}
		});
	};

	var logout = function() {
		return $http({
			method: 'GET',
			url: '/logout'
		});
	};

	var changeAdminName = function(name, pass, newname) {
		return $http({
			method: 'POST',
			url: '/account/changename',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				username: name,
				password: pass,
				newname: newname
			}
		});
	};

	var changeAdminPass = function(name, pass, newpass) {
		return $http({
			method: 'POST',
			url: '/account/changepass',
			headers: {
				xsrfHeaderName: 'sercurity'
			},
			data: {
				username: name,
				password: pass,
				newpass: newpass
			}
		});
	};


	var redirect = function(state) {
		$state.go(state);
	}

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
		redirect: function(state) {
			return redirect(state);
		},
		login: function(username, password) {
			return login(username, password);
		},
		logout: function(){
			return logout();
		},
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
		},
		changeName: function(name, pass, newname) {
			return changeAdminName(name, pass, newname);
		},
		changePass: function(name, pass, newpass) {
			return changeAdminPass(name, pass, newpass);
		}
	}

}]);