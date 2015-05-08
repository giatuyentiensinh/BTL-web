'use strict';

var app = angular.module('App', [
	'ui.router',
	'ui.load',
	'App.controller',
	'ngCookies'
]);

angular.module('App.controller', [])
	.controller('AppCtrl', ['$scope', function($scope) {

	}]);

app.config([
			'$stateProvider', '$urlRouterProvider',
	function($stateProvider,   $urlRouterProvider) {

		$stateProvider
			.state('main', {
				url: '/main',
				abstract: true,
				templateUrl: 'tpl/pages/home.html',
				controller: 'AppCtrl',
				resolve: {
							deps: ['uiLoad', function(uiLoad) {
								return uiLoad.load(['js/productCtrl.js']);
							}]
						}
			})
			.state('main.home', {
				url: '/home',
				views: {
					'header': {
						templateUrl: 'tpl/blocks/header.html'
					},
					'footer': {
						templateUrl: 'tpl/blocks/footer.html'
					},
					'left-menu': {
						templateUrl: 'tpl/blocks/left-nav.html'
					},
					'banner': {
						templateUrl: 'tpl/banner.html'
					},
					'contain': {
						templateUrl: 'tpl/pages/products.html'
					},
					'recommend': {
						templateUrl: 'tpl/recommend.html'
					}
				}
			})
			.state('product', {
				url: '/product',
				abstract: true,
				templateUrl: 'tpl/pages/product-detail.html',
				controller: 'AppCtrl'
			})
			.state('product.detail', {
				url: '/detail/:id',
				views: {
					'header': {
						templateUrl: 'tpl/blocks/header.html'
					},
					'footer': {
						templateUrl: 'tpl/blocks/footer.html'
					},
					'left-menu': {
						templateUrl: 'tpl/blocks/left-nav.html'
					},
					'detail-list': {
						templateUrl: 'tpl/list-detail.html'
					}				
				}
			})
			.state('product.all', {
				url: '/all',
				views: {
					'header': {
						templateUrl: 'tpl/blocks/header.html'
					},
					'footer': {
						templateUrl: 'tpl/blocks/footer.html'
					},
					'left-menu': {
						templateUrl: 'tpl/blocks/left-nav.html'
					},
					'detail-list': {
						templateUrl: 'tpl/pages/products.html'
					}				
				}
			})
			.state('cart', {
				url: '/cart',
				abstract: true,
				templateUrl: 'tpl/pages/cart.html',
				controller: 'AppCtrl'
			})
			.state('cart.cart', {
				url: '/cart',
				views: {
					'header': {
						templateUrl: 'tpl/blocks/header.html'
					},
					'footer': {
						templateUrl: 'tpl/blocks/footer.html'
					}
				}
			})
			.state('contact', {
				url: '/contact',
				abstract: true,
				templateUrl: 'tpl/pages/contact.html',
				controller: 'AppCtrl'
			})
			.state('contact.contact', {
				url: '/contact',
				views: {
					'header': {
						templateUrl: 'tpl/blocks/header.html'
					},
					'footer': {
						templateUrl: 'tpl/blocks/footer.html'
					},
					'page-contact': {
						templateUrl: 'tpl/page-contact.html'

					}
				}
			})
			.state('404', {
				url: '/filenotfound',
				templateUrl: 'tpl/pages/paper-notfound.html'
			})
			;

			$urlRouterProvider.otherwise('/filenotfound');
}]);