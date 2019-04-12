import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { FormControl,FormGroup, Validators} from '@angular/forms';
import { Constants } from 'src/app/constants/Constants';
import { UserService } from 'src/app/services/user.service';

declare var $:any;

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  
  posts;
  opportunities;
  totalPosts=0;
  totalOpps=0;
  manageAgentUrl;
  project: FormGroup;
  organizationType = ["Public Company","Educational Institution","Self-Employed","Government Agency","Not for Profit","Sole Proprietorship","Privately Held","Partnership"];
  organizationSize=["1-10","11-50","51-200","201-500","501-1000","1001-5000","5001-10000","10000+"];
  sectors = [
    "Banking",
    "Care",
    "Children's Charity",
    "Civic & Social Organisation",
    "Construction",
    "Education Management",
	"Engineering",
    "Financial Services",
    "Humanitarianism",
    "Information Technology & Services",
    "International Development",
    "International Trade",
    "Legal Services",
    "Marketing",
    "Media",
    "Public Policy",
    "Research",
    "Think Tank"
  ];
  countryList = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas"
  ,"Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands"
  ,"Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica"
  ,"Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea"
  ,"Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana"
  ,"Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India"
  ,"Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia"
  ,"Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania"
  ,"Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia"
  ,"New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal"
  ,"Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles"
  ,"Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","St. Lucia","Sudan"
  ,"Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia"
  ,"Turkey","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States","United States Minor Outlying Islands","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)"
  ,"Yemen","Zambia","Zimbabwe"];

  constructor(private company:CompanyService,private user:UserService) { 
    
  }

  ngOnInit() {
    this.manageAgentUrl=Constants.AGENT_MANAGE_URL;
    this.project=new FormGroup({
      title: new FormControl("",Validators.compose([
        Validators.required
      ])),
      description: new FormControl("",Validators.compose([
        Validators.required
      ]))
    });
    this.company.getPostByCompanyName(this.company.companyObject.name).subscribe((data:any)=>{
        this.posts=data;
        this.totalPosts=data.length;
    });
    this.company.getOppByCompanyName(this.company.companyObject.name).subscribe((data:any)=>{
        this.opportunities=data;
        this.totalOpps=data.length;
    });
  }
  compareCompany()
  {
    return JSON.stringify(this.company.companyObject)==JSON.stringify(this.company.editCompanyObject);
  }

  editCompany()
  {
    this.company.companyProfileUpdate();
  }
  resetEditCompanyObject()
  {
    this.company.editCompanyObject={};
    Object.assign(this.company.editCompanyObject,this.company.companyObject);
  }

  addProject()
  {
     if(this.company.editCompanyObject.project)
     {
        this.company.editCompanyObject.project.push(this.project.value);
     }
     else
     {
       let project=[];
       project.push(this.project.value);
       this.company.editCompanyObject['project']=project;
      }
      this.company.companyProfileUpdate();
      this.project.reset();
      this.closeModal();
  }
  deleteProject(index)
  {
      this.company.editCompanyObject.project.splice(index,1);
      this.company.companyProfileUpdate();
  }
  changeProfile() {
		$(document).ready(function () {
			$('#fileInput').trigger('click');
		});
	}

	profileChanged(event) {
		let profilePic: File = event.target.files.item(0);
		this.company.changeProfileImage(profilePic);
		}
  closeModal()
  {
    $(document).ready(function () {
      $('.modal').modal('hide');
     });
  }
 
}
