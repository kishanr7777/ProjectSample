import { Component } from '@angular/core';
import { Router} from '@angular/router';
import { Constants } from './constants/Constants';
import { StudentModule } from './student/student.module';
import { BusinessModule } from './business/business.module';
import { WelcomeModule } from './welcome/welcome.module';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'tconnect';

  constructor(private router: Router, private auth: AuthService) {


  }
  ngOnInit() {
    // this.auth.isAuthenticated().subscribe(success => {
    //   if (localStorage.getItem(Constants.TYPE) === Constants.STATUS_STUDENT) {
    //     this.router.resetConfig([
    //       {
    //         path: '', loadChildren: () => StudentModule
    //       }
    //     ])
    //     this.router.navigateByUrl('')
    //   }
    //   else if (localStorage.getItem(Constants.TYPE) === Constants.STATUS_AGENT) {
    //     this.router.resetConfig([{
    //       path: '', loadChildren: () => BusinessModule
    //     }]);
    //     this.router.navigateByUrl('')
    //   }
    // }, error => {
    //   this.router.resetConfig([{
    //     path: '', loadChildren: () => WelcomeModule,
    //   }]);
    //   localStorage.removeItem(Constants.TOKEN);
    //   localStorage.removeItem(Constants.TYPE);
    //   this.router.navigateByUrl('')
    // });
  }
}
