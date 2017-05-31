- Registration process must be splitted into three parts ([see](https://dzone.com/articles/pedantic-guide-restful)):
    - email contains link -> GET(link) -> user get redirected to the client application (not directly to the service)
    - client application receives GET(link) -> proceed the confirmation of the registration doing a POST(link)
    - service receives POST(link) -> changing the status of the account to ACTIVE and responds OK (HTTP 200) to the client application
- Make user validation by default validation utils (@Valid annotation)
- Add logging
- Solve problem with @Validated in *Properties = uncomment that annotation
- Maybe bind in some way UserRole and UserStatus
- Make requests to DB from tests in right way, without using *Repository classes/ORM/jOOQ