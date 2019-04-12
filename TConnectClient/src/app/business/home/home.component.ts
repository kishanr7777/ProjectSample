import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { UserService } from 'src/app/services/user.service';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { Constants } from 'src/app/constants/Constants';
declare var $: any;

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  attachments : any[] = [];
  fileSize:number=0;
  filePrompts:boolean=false;
  fileMessage:string='';
  writePostFormData :FormData;
  tab: number = 0;
  postMessage='';
  universitiesList = [];
  selected: any[] = [];
  postForm: FormGroup;
  oppForm:FormGroup;
  posts;
  totalPosts:number=0;
  opportunities;
  totalOpps:number=0;
  files = [];
  pics = [];
  videos = [];
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
  jobTypes=["Volunteer","Internship","Industrial Placement/Practicum","Graduate"];
  jobFunctions=["Administration","Advertising, Media & PR","Business Development","Community & Social Services","Construction / Contracting","Consulting","Counselling"
  ,"Customer/Technical Support","Data & Analytics","Design / Art","Education / Teaching / Training","Engineering – Civil / Mechanical / Other","Engineering – Web / Software"
  ,"Entrepreneurship","Environmental / Sustainability Management","Finance","Fundraising & Event Management","General Management","Healthcare Services","Healthcare Services"
  ,"Human Resources","Informational Technology","Legal","Library Science","Logistics & Supply Chain","Marketing – Brand Management","Marketing – General Military & Protective Services"
  ,"Operations / Production","Political Organising / Lobbying","Product / Project Management","Purchasing","Quality Assurance","Real Estate","Research","Sales","Veterinary / Animal Care"
  ,"Writing / Editing","Other"];

	constructor(private auth: AuthService, private company: CompanyService, private user: UserService) {
	}

  ngOnInit() {
    this.auth.getUniversities().subscribe((data: any) => {
      data.forEach(element => {
        this.universitiesList.push(element.name);
      });
    });
    this.postFormInit();
    this.oppFormInit();
    this.company.getPostByAgentEmail(this.user.agent.email).subscribe((data:any)=>{
        this.posts=data;
        this.totalPosts=data.length;
        console.log(data);
    });
    this.company.getOppsByAgentEmail(this.user.agent.email).subscribe((data:any)=>{
        this.opportunities=data;
        this.totalOpps=data.length;
    })
  }
  postFormInit()
  {
    this.postForm = new FormGroup({
      content: new FormControl("",Validators.required),
      universities: new FormControl(this.selected,Validators.required),
      companyName: new FormControl(this.company.companyObject.name),
      agentEmail: new FormControl(this.user.agent.email),
      visibleToAll: new FormControl(false)
    });
  }
  oppFormInit()
  {
          this.oppForm=new FormGroup({
            headline: new FormControl("",Validators.required),
            jobType: new FormControl("",Validators.required),
            location: new FormControl("",Validators.required),
            jobFunction: new FormControl("",Validators.required),
            description:new FormControl("",Validators.required),
            url: new FormControl("",Validators.required),
            deadline: new FormControl("",Validators.required),
            visibleToAll:new FormControl(false),
            universities:new FormControl(this.selected,Validators.required),
            companyName:new FormControl(this.company.companyObject.name),
            agentEmail:new FormControl(this.user.agent.email)
          });
  }
  writepost() {
      console.log(this.attachments);
     this.writePostFormData=this.postFormData("postDto",this.postForm.value);
     this.company.writePost(this.writePostFormData).subscribe((data:any)=>{
      if(data[Constants.ERROR])
      {
        this.postMessage="Post Uploading Failed!!";
      }
      else
      {
        this.posts.push(data);
        this.tabChange();
      }
    });
  }
  writeOpp()
  {
    this.writePostFormData=this.postFormData("opportunityDto",this.oppForm.value);
    this.company.writeOpp(this.writePostFormData).subscribe((data:any)=>{
      if(data[Constants.ERROR])
      {
        this.postMessage="Opportunity Uploading Failed!!";
      }
      else
      {
        this.opportunities.push(data);
        this.tabChange();
      }
     });
  }
  tabChange()
  {
    this.attachments=[];
    this.postMessage='';
    this.fileMessage='';
    this.filePrompts=false;
    this.selected=[];
    this.pics=[];
    this.videos=[];
    this.files=[];
    this.postFormInit();
    this.oppFormInit();
  }
  selectUniversities()
  {
    if(this.selected.length>0)
    {
      for (let j = 0; j < this.selected.length; j++) {
        if (this.selected[j] === "All Universities") {
          this.selected = [];
          this.selected.push("All Universities");
        }
      }
      if(this.tab===0)
      {
        if(this.selected[0] === "All Universities")
          this.postForm.controls.universities.reset(this.universitiesList);
        else
          this.postForm.controls.universities.reset(this.selected);
      }
      else if(this.tab===1)
      {
        if(this.selected[0] === "All Universities")
          this.oppForm.controls.universities.reset(this.universitiesList);
        else
          this.oppForm.controls.universities.reset(this.selected);
      }
    }
    else
    {
        this.oppForm.controls.universities.reset();
        this.postForm.controls.universities.reset();
    }
  }
  
  postFormData(dto,form)
  {
    let postFormData=new FormData();
    this.attachments.forEach(element => {
        postFormData.append("files",element);
    });
    postFormData.append(dto,JSON.stringify(form));
    return postFormData;
  }
  attachFileInput()
  {
    if(this.tab===0)
    {
      $(document).ready(function () {
        $('#attachmentFileInput').trigger('click');
      });
    }
    else if(this.tab==1)
    {
      $(document).ready(function () {
        $('#attachmentFileInputOpps').trigger('click');
      });
    }
  }
  PreviewFiles(files)
  {
    let tempAttachments=[];
    tempAttachments.push(files);
    tempAttachments.forEach(element=>{
      
      for(let index=0;index<element.length;index++)
      {
        this.attachments.push(element[index]);
        let attachment = {};
        this.checkSize(element[index]);
        if(this.isPic(element[index].name))
        {
                let reader=new FileReader();
                reader.addEventListener("load",()=>{
                  attachment['preview'] = reader.result;
                  attachment['file'] = element[index];
                  this.pics.push(attachment);
                },false);
                reader.readAsDataURL(element[index]);
            }
            else if(this.isVideo(element[index].name))
            {
              let reader=new FileReader();
              reader.addEventListener("load",()=>{
                attachment['preview'] = reader.result;
                attachment['file'] = element[index];
                this.videos.push(attachment);
              },false);
              reader.readAsDataURL(element[index]);
            }
            else
            {
              attachment['preview'] = element[index].name;
              attachment['file'] = element[index];
              this.files.push(attachment);
            }
        }
      });
  }
  checkSize(element:any)
  {
    if(this.isVideo(element.name))
    {      
            if(element.size>(1000*1000*20))
            {
              this.fileMessage=element.name + " size greater than 20 MB ";
              this.filePrompts=true;
            }
    }
    else
    {
      if(element.size>(1000*1000*5))
      {
        this.fileMessage=element.name + " size greater than 5 MB ";
        this.filePrompts=true;
      }
    }
  }
  removeAttachment(attachment:any)
  {
    if(this.isPic(attachment.file.name))
    {
      this.pics.splice(this.pics.indexOf(attachment),1);
    }
    else if(this.isVideo(attachment.file.name))
    {
      this.videos.splice(this.videos.indexOf(attachment),1);
    }
    else
    {
      this.files.splice(this.files.indexOf(attachment),1);
    }
    this.attachments.splice(this.attachments.indexOf(attachment.file),1);
    this.fileMessage="";
    this.filePrompts=false;
    this.attachments.forEach(element=>{
      this.checkSize(element);
    });
  }
	isPic(fileName) {
		let x = fileName.split('').reverse().join('').split('.')[0].split('').reverse().join('').toLowerCase();
		if (x === "png" || x === "jpg" || x === "jpeg" || x === "gif" || x === "ico" || x==="bmp") return true;
		return false
	}
	isVideo(fileName) {
		let x = fileName.toString().split('').reverse().join('').split('.')[0].split('').reverse().join('').toLowerCase();
		if (x === "mp4" || x === "ogg" || x==="ogv" || x==="3gp" || x==="flv" || x==="mkv") return true;
		return false;
  }
}
