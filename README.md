# BookManagement
@AuthController:
1. Create user
2. SignIn user and get Jwt token
3. Get new token With refresh token
4. Change password by email and previouse passwod
@User controller
1. get user by user id
2. update user by id
3. delete user by id
4. get by user email
5. delete book from user book list by user id and book id
6. add book by user id and book id
@BookController
1. create new book
2. update book by book id
3. get single book by id
4. delete book by book id
5. get user book list by user id
6. get all book

/api/v1/auth/** permitall
/api/v1/book/All permitall
/api/v1/admin/**").hasAnyRole(Role.ADMIN.name())
/api/v1/user/**").hasAnyRole(Role.USER.name())
/api/v1/user/delete/{id}).hasAnyRole(Role.ADMIN.name()
/api/v1/book/delete/{id}).hasAnyRole(Role.ADMIN.name()

