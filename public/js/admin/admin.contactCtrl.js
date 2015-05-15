admin.controller('AdminContactCtrl', ['$scope', '$modal', 'AdminService',
	function($scope, $modal, AdminService) {
		AdminService.readBill()
			.success(function(data, status, headers, config) {
				// console.log(data);
				// console.log("success");
				$scope.count = data.length;
				$scope.bills = data;
			})
			.error(function(data, status, headers, config) {
				if(data === 'not login') {
					AdminService.redirect('login');
				} else {
					console.log("Error to server");
					console.log(data);
				}
			});

		AdminService.readComment()
			.success(function(data, status, headers, config) {
				// console.log(data);
				// console.log("success");
				$scope.countComment = data.length;
				$scope.comments = data;
			})
			.error(function(data, status, headers, config) {
				if(data === 'not login') {
					AdminService.redirect('login');
				} else {
					console.log("Error to server");
					console.log(data);
				}
			});


		$scope.showBillId = function(bill) {
			// console.log(bill);

			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'showBillId.html',
				controller: 'ModalBillCtrl',
				size: 'lg',
				resolve: {
					bill: function() {
						return bill;
					}
				}
			});

			modalInstance.result.then(function(bill) {
				// console.log(bill);
			},
			function() {
				console.log('dismiss');
			});
		}

		$scope.showCommentId = function(comment) {
			// console.log(comment);

			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'showCommentId.html',
				controller: 'ModalCommentCtrl',
				// size: 'lg',
				resolve: {
					comment: function() {
						return comment;
					}
				}
			});

			modalInstance.result.then(function(comment) {
				// console.log(comment);
			},
			function() {
				console.log('dismiss');
			});
		}

	}]);

admin.controller('ModalBillCtrl', ['$scope', '$modalInstance', 'bill', 'AdminService',
	function($scope, $modalInstance, bill, AdminService) {

		$scope.username = bill.username;
		$scope.phonenumber = bill.phonenumber;
		$scope.email = bill.email;
		$scope.address = bill.address;
		$scope.items = [];

		for(var i = 0; i < bill.item.length; i++) {
			AdminService.phoneId(bill.item[i].id)
				.success(function(data, status, headers, config) {
					// console.log(data);
					for(var j = 0; j < bill.item.length; j++) {
						if(data.id === bill.item[j].id)
							data.quantity = bill.item[j].quantity;						
					}
					$scope.items.push(data);
				})
				.error(function(data, status, headers, config) {
					if(data === 'not login') {
						AdminService.redirect('login');
					} else {
						console.log('error find item in bill');
					}
				})			
		}

		$scope.ok = function() {
			$modalInstance.close(bill);
		}

}]);

admin.controller('ModalCommentCtrl', ['$scope', '$modalInstance', 'comment',
	function($scope, $modalInstance, comment) {
		$scope.name = comment.name;
		$scope.email = comment.email;
		$scope.subject = comment.subject;
		$scope.content = comment.content;

		$scope.ok = function() {
			$modalInstance.close(comment);
		}
}]);