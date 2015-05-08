'use strict';

var admin = angular.module('admin', [
	'ui.router'
]);

admin.config([
			'$stateProvider', '$urlRouterProvider',
	function($stateProvider,   $urlRouterProvider) {
		$urlRouterProvider.otherwise('/product');

		$stateProvider
			.state('product', {
				url: '/product',
				templateUrl: 'tpl/pages/admin-product.html',
				controller: 'AdminProductCtrl'
			})
			.state('contact', {
				url: '/contact',
				templateUrl: 'tpl/pages/admin-contact.html',
				controller: 'AdminContactCtrl'
			})
			.state('account', {
				url: '/account',
				templateUrl: 'tpl/pages/admin-account.html',
				controller: 'AdminAccountCtrl'
			});

}]);