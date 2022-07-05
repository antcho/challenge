# Anthony's notes

The challenge is done with the required features.

It's a SpringBoot application. \
You can play around with it by launching it and trying the different endpoints. There is by default 2 companies and 2 users created.

*/users/1 ->* John \
*/users/2 ->* Jessica

*/companies/1* -> Tesla \
*/companies/2* -> Apple

Exercise 1 : 2 endpoints were made, with 3 request parameters each : companyId; userId and value.
Examples :
* */action/distributeGift?companyId=1&userId=1&value=100*
* */action/distributeMeal?companyId=2&userId=2&value=50* \
You can test if they work by checking the users (or companies), and see the updated data.

Exercise 2 : The user balance is automatically visible when checking the /users endpoint. It is split in two :
one balance for meal and the other for gift.

Since all the code was done in a row, I only made one commit.

You can also check an endpoint to know the expiration dates of the cards for a user :
*/users/{userId}/expirationDates*

I made some unit tests for the class DepositService, to cover it and to make sure its methods behave correctly.\
I didn't make it for other class since it would be the same.

I know the code isn't perfect, as there are some *null* values that are not checked, or other things not business accurate (like having a company to distribute a card to a employee of an other company),
but this is what I've done in the time I has to do the challenge.

Thank you for reading.

Anthony
