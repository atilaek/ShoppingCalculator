#Shopping Calculator Demo Project

## INSTRUCTIONS

The goods that can be purchased, which are all priced in GBP, are:

`Soup ? 65p per tin`

`Bread ? 80p per loaf`

`Milk ? £1.30 per bottle`

`Apples ? £1.00 per bag`

Current special offers are:

`Apples have 10% off their normal price this week`

`Buy 2 tins of soup and get a loaf of bread for half price`

The program should accept a list of items in the basket and output the subtotal, the special offer discounts and the final price.
Input should be via the command line in the form PriceBasket item1 item2 item3 ?

For example: PriceBasket Apples Milk Bread

Output should be to the console, for example:

`Subtotal: £3.10`

`Apples 10% off: -10p`

`Total: £3.00`

If no special offers are applicable, the code should output:

`Subtotal: £1.30`

`(no offers available)`

`Total: £1.30`

## Project Details:

The project has designed and implemented with extreme flexibility for extensions.

1- Project runs through reading two json files as a representative of db.

2- Promotions.json and code can hold/process various types of products both as "purchase-required products" and "discount products"

3- Products.json and code holds stores products with storage amount. System also checks if it runs out.

## How To Run

###On Cmd

`1- copy paste products.json and promotions.jsFon to C:\`

`2- go to project folder`

`3- execute "mvn clean package" f.e. c:\ShoppingCalculator>mvn clean package`

`4- run project jar file with products you want to calculate`

`f.e. c:\ShoppingCalculator>java -jar ./target/ShoppingCalculator-0.1.jar Soup Soup Bread Apple`

##Special Thanks To:
https://www.mkyong.com/

https://dzone.com/

https://stackoverflow.com/

