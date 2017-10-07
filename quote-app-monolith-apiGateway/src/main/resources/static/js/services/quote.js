'use strict'

// remove square brackets from below statement as we have divided angular.module('app.services')  into multiple files.
angular.module('app.services') 

.factory('QuoteService', function($resource) {
	return $resource('/api/quote/:id', {id:'@_id'}, {
		random: {
			method: 'GET',
			url: '/api/quote/random'
			
		}
	});
});
