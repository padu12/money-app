classDiagram
direction BT
class AuthController {
  + AuthController(PersonValidator, RegistrationService, PasswordValidator) 
  - PasswordValidator passwordValidator
  - PersonValidator personValidator
  - RegistrationService registrationService
  + getLoginPage() String
  + getRegistrationPage(Person) String
  + postRegistration(Person, BindingResult, Users) String
}
class AuthProviderImpl {
  + AuthProviderImpl(PersonDetailsService) 
  - PersonDetailsService personDetailsService
  + authenticate(Authentication) Authentication
  + supports(Class~?~) boolean
}
class FrontController {
  + FrontController(RecordingService, PersonService) 
  - PersonService personService
  - RecordingService recordingService
  + about() String
  + home() String
  + investing() String
  + makeTransaction(Recording, String) String
  + none() String
  + report(Model) String
  + transaction(Recording, Model) String
}
class MainController {
  + MainController(RecordingService) 
  - RecordingService recordService
  + change(int, Recording) String
  + create(Recording) String
  + delete(int) String
  + edit(Model, int) String
  + goHome(Model) String
  + newRecord(Recording) String
}
class MoneyAppApplication {
  + MoneyAppApplication() 
  + main(String[]) void
}
class PasswordValidator {
  + PasswordValidator() 
  + supports(Class~?~) boolean
  + validate(Object, Errors) void
}
class PeopleRepository {
<<Interface>>
  + findByUsername(String) Optional~Person~
}
class Person {
  + Person(String, String, String) 
  + Person() 
  - int id
  - String name
  - String password
  - List~Recording~ recordings
  - String repeatPassword
  - String username
  + getId() int
  + getName() String
  + getPassword() String
  + getRepeatPassword() String
  + getUsername() String
  + setId(int) void
  + setName(String) void
  + setPassword(String) void
  + setRepeatPassword(String) void
  + setUsername(String) void
  + toString() String
}
class PersonDetails {
  + PersonDetails(Person) 
  - Person person
  + getAuthorities() Collection~GrantedAuthority~
  + getPassword() String
  + getPerson() Person
  + getUsername() String
  + isAccountNonExpired() boolean
  + isAccountNonLocked() boolean
  + isCredentialsNonExpired() boolean
  + isEnabled() boolean
}
class PersonDetailsService {
  + PersonDetailsService(PeopleRepository) 
  - PeopleRepository peopleRepository
  + loadUserByUsername(String) UserDetails
}
class PersonService {
  + PersonService(PeopleRepository, RecordingRepository) 
  - PeopleRepository peopleRepository
  - RecordingRepository recordingRepository
  + getBalance() int
  + getIncome() int
  + getOutgo() int
  + getSessionPerson() PersonDetails
}
class PersonValidator {
  + PersonValidator(PersonDetailsService) 
  - PersonDetailsService personDetailsService
  + supports(Class~?~) boolean
  + validate(Object, Errors) void
}
class Recording {
  + Recording() 
  + Recording(Person, String, String, int, String) 
  - String description
  - int id
  - String month
  - Person personId
  - String type
  - int value
  + getDescription() String
  + getId() int
  + getMonth() String
  + getPersonId() Person
  + getType() String
  + getValue() int
  + setDescription(String) void
  + setId(int) void
  + setMonth(String) void
  + setPersonId(Person) void
  + setType(String) void
  + setValue(int) void
}
class RecordingRepository {
<<Interface>>
  + findAllByType(String) List~Recording~
  + findByPersonId(Person) List~Recording~
}
class RecordingService {
  + RecordingService(RecordingRepository) 
  - RecordingRepository recordingRepository
  - SessionFactory sessionFactory
  + delete(int) void
  + findAll() List~Recording~
  + findAllIncome() List~Recording~
  + findAllIncomeByMonthIn() int[]
  + findAllIncomeByMonthOut() int[]
  + findAllOutgo() List~Recording~
  + findById(int) Recording
  + findByPerson() List~Recording~
  + save(Recording, String) void
}
class RegistrationService {
  + RegistrationService(PeopleRepository, UsersService) 
  - PeopleRepository peopleRepository
  - UsersService usersService
  + register(Person, Users) void
}
class SecurityConfig {
  + SecurityConfig(PersonDetailsService) 
  - PersonDetailsService personDetailsService
  + configure(HttpSecurity) SecurityFilterChain
  + getPasswordEncoder() PasswordEncoder
  ~ registerProvider(AuthenticationManagerBuilder) void
}
class Users {
  + Users(Person, int, int, int) 
  + Users() 
  - int balance
  - int id
  - int income
  - int outgo
  - Person person
  + getBalance() int
  + getId() int
  + getIncome() int
  + getOutgo() int
  + getPerson() Person
  + setBalance(int) void
  + setId(int) void
  + setIncome(int) void
  + setOutgo(int) void
  + setPerson(Person) void
}
class UsersRepository {
<<Interface>>
  + findByPerson(Person) Optional~Users~
}
class UsersService {
  + UsersService(UsersRepository) 
  - UsersRepository usersRepository
  + findOne() Users
  + save(Users) void
  + transaction(String) void
}

AuthController "1" *--> "passwordValidator 1" PasswordValidator 
AuthController "1" *--> "personValidator 1" PersonValidator 
AuthController "1" *--> "registrationService 1" RegistrationService 
AuthProviderImpl "1" *--> "personDetailsService 1" PersonDetailsService 
FrontController "1" *--> "personService 1" PersonService 
FrontController "1" *--> "recordingService 1" RecordingService 
MainController "1" *--> "recordService 1" RecordingService 
Person "1" *--> "recordings *" Recording 
PersonDetails "1" *--> "person 1" Person 
PersonDetailsService "1" *--> "peopleRepository 1" PeopleRepository 
PersonDetailsService  ..>  PersonDetails : «create»
PersonService "1" *--> "peopleRepository 1" PeopleRepository 
PersonService "1" *--> "recordingRepository 1" RecordingRepository 
PersonValidator "1" *--> "personDetailsService 1" PersonDetailsService 
Recording "1" *--> "personId 1" Person 
RecordingService "1" *--> "recordingRepository 1" RecordingRepository 
RegistrationService "1" *--> "peopleRepository 1" PeopleRepository 
RegistrationService "1" *--> "usersService 1" UsersService 
SecurityConfig "1" *--> "personDetailsService 1" PersonDetailsService 
Users "1" *--> "person 1" Person 
UsersService "1" *--> "usersRepository 1" UsersRepository 
