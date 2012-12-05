Ensemble B2 (Building Block)


Release: 3.4.121204
=>[x] Update all dependencies
	=> b2context to 1.3.00
	=> Simple-XML to 2.6.9
	=> Apache HTTP client to 4.2.2
	=> joda-time to 2.1	
=> [x]Clean up formatting / layout
=> [x] URL Encode all query data sent over the wire
	=> [x] fixed issue with Encoded content. Need to double encode so when decoded the % doesn't go away. 	
=> [x] Fix to use the new 3.4 plugin code
=> [x]Issue with vbte entry point not registering
	=> Duh you have to enable it each time after you deploy.
=> [x] SAX parser issue - should show 401 error not sax parser error. fixed it so now it does.


Time Log:
Date	Time	What
11/28 	120 	block setup on dev environment - fix HTML layout issues in block, add URL encoding to search.
11/30	150		worked on testing issues with URL encoded searches  by encoding the %
12/4	210		fixed the embeds to use the new 3.4 plugin code, tested everything to make sure it works.