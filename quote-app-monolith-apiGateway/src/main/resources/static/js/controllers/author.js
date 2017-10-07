'use strict'

angular.module('app.controllers',[])
// Writing the controller to interact with service and get data.
// Pass the author id to uniquely identify them.

.controller('GetAuthorQuoteCtrl', function($scope, $stateParams, AuthorService) {
		AuthorService.get({authorId:$stateParams.authorId}, function(author) {
	$scope.author = author;
	});
});