# BookManagement
@AuthController:
1. Create user (body:{   {
    "userName":"niber_kumar",
    "email":"niber@gmail.com",
    "password":"123456",
    "fullName":"niber kumar tarofdar"
}
 }) 
2. SignIn user and get Jwt token
3. Get new token With refresh token
4. Change password by email and previouse passwod
@User controller
1. get user by userid
2. update user by userid
3. delete user by userid
4. get by user email request param
5. delete book from user book list by userid and bookid
6. add book by userid and bookid
@BookController
1. create new book(Body:{
{
    "title":"nice book",
    "pageCount":"39",
    "authors":["a","b"],
    "description":"description book nice book",
    "categories":["cat1","cat2"],
    "status":"pushlished"
}
})
2. update book by book id
3. get single book by bookid
4. delete book by book bookid
5. get user book list by userid
6. get all book

/api/v1/auth/** permitall 
/api/v1/book/All permitall 
/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name()) 
/api/v1/user/**").hasAnyAuthority(Role.USER.name()) 


