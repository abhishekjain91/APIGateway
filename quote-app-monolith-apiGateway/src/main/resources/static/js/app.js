'use strict'

var app = angular.module('app', [
    'ui.router',
    'ngResource',
    'app.services',
    'app.controllers'

]);

app.config(function($urlRouterProvider, $stateProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state("quote", {
            url: "/",
           templateUrl : "templates/quote.html",
            controller: "RandomQuoteCtrl"
        })
        .state("add", {
            url: "/add",
            templateUrl : "templates/add.html",
            controller: "SaveQuoteCtrl"
        })
        // Added a new routing mechanism to redirect to the new page.
        // We also add the controller to be called in this process.
		.state("author", {
	    	url: "/author/{authorId}",
            templateUrl : "templates/author.html",
            controller: "GetAuthorQuoteCtrl"
		});
});
