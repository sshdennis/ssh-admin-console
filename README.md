# Store Listing Admin Console

## Description

Store Listing Admin Console is a CLI application. You can register new user, create listings, and get the listing and category.

* User
  * REGISTER
* LISTING
  * CREATE
  * GET
  * DELETE
* CATEGORY
  * GET
  * GETTOP

## Environment

* JDK 8
* Maven 3.5 or above

## Build

```
$ ./build.sh
```

## Run

```
$ ./run.sh
```

## Usage
```
Usage -
	REGISTER <username>
	CREATE_LISTING <username> <title> <description> <price> <category>
	DELETE_LISTING <username> <listing_id>
	GET_LISTING <username> <listing_id>
	GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}
	GET_TOP_CATEGORY <username>
    EXIT
```

## Example

### Register new user
```
# REGISTER user1
Success
```

### Create new listing
```
# CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'
100001
```

### Get listing by ID
```
# GET_LISTING user1 100001
Phone model 8|Black color, brand new|1000|2019-12-08 21:33:41|Electronics|user1
```

### Delete listing by ID
```
# DELETE_LISTING user2 100003
Success
```

### Get category
```
# GET_CATEGORY user1 'Electronics' sort_time dsc
T-shirt|White color|20|2019-12-08 21:34:11
Black shoes|Training shoes|100|2019-12-08 21:33:53
```

### Get top category
```
# GET_TOP_CATEGORY user2
Electronics
```

### Exit CLI
```
# EXIT
```
