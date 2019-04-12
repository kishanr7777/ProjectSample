import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from 'src/app/constants/Constants';
import { Observable } from 'rxjs';
import { StudentModule } from '../student/student.module';
import { BusinessModule } from '../business/business.module';
import { WelcomeModule } from '../welcome/welcome.module';
import { reject } from 'q';


@Injectable({
  providedIn: 'root'
})

export class AuthService {
  url = Constants.URL;
  constructor(private http: HttpClient) { }

  registerStudent(studentData) {
    let headers = new HttpHeaders().append('Accept', 'application/json')
    return this.http.post(this.url + "user/register/student", studentData, { headers: headers })
  }

  registerAgent(agentData) {
    let headers = new HttpHeaders().append('Accept', 'application/json')
    return this.http.post(this.url + "user/register/agent", agentData, { headers: headers });
  }
  headers: HttpHeaders;
  verifyUser(username, password) {
    var base64Credential: string = btoa(username + ':' + password);
    this.headers = new HttpHeaders().append('Accept', 'application/json').append("Authorization", "Basic " + base64Credential);
    return this.http.get(this.url + "login", { headers: this.headers });
  }

  logout() {
    return this.http.get(this.url + "logout");
  }
  getToken(): String{
    return localStorage.getItem(Constants.TOKEN);
  }

  isAuthenticated()
  {
    return this.http.get(this.url+'isAuthorized');
  }

  checkAuthenticated()
  {
    return new Promise(resolve=>{
      this.http.get(this.url+'isAuthorized').subscribe(success=>{
        resolve();
      },
      error=>{
         localStorage.removeItem(Constants.TOKEN);
         localStorage.removeItem(Constants.TYPE);
        resolve();
       
      })
    }).catch(error=>{
      console.log(error);
    })
  }

  changePassword(password,token){
    return this.http.post(this.url+'password/change',{password:password,token:token});
  }

  forgotPassword(email)
  {
    let headers = new HttpHeaders().append('Accept', 'application/json')
    return this.http.post(Constants.URL+"password/resetLink/generate",email, {headers:headers} );
  }

  getUniversities()
  {
    return this.http.get(Constants.URL+"list/university");
  }
  manageAgent(agentData)
  {
    return this.http.post(Constants.URL+"user/manage/agent",agentData);
  }
}
