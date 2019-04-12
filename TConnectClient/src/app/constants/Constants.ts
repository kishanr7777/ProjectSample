import { WelcomeModule } from "../welcome/welcome.module";

export const Constants = {
     URL: "http://192.168.0.22:10001/",
     RURL: "http://192.168.0.140:10001/",
     AGENT_MANAGE_URL:window.location.origin,
     STATUS_LOGED_IN: "logged in",
     STATUS_LOGED_OUT: "logged out",
     STATUS_UNAUTHORIZED: "unauthorized",
     STATUS_FAILED: "failed",
     STATUS_AGENT :"agent",
     STATUS_STUDENT :"student",
     TOKEN : 'token',
     TYPE : 'type',
     STATUS : 'status',
     EMAIL_PATTERN : '^[\\d\\w]+(\\.[\\d\\w]+)*@[\\d\\w]+(\\.[\\d\\w]+)*\\.[\\d\\w]{2,}$',
     PASSWORD_PATTERN :'(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!@#$%^&]).{8,}',
     MESSAGE:'message',
     EMAIL:'email',
     PASSWORD_CHANGED:'passwordChange',
     ERROR:'error',
     
}