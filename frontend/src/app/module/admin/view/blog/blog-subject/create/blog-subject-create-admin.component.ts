import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




import {BlogSubjectAdminService} from 'src/app/shared/service/admin/blog/BlogSubjectAdmin.service';
import {BlogSubjectDto} from 'src/app/shared/model/blog/BlogSubject.model';
import {BlogSubjectCriteria} from 'src/app/shared/criteria/blog/BlogSubjectCriteria.model';
@Component({
  selector: 'app-blog-subject-create-admin',
  templateUrl: './blog-subject-create-admin.component.html'
})
export class BlogSubjectCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validBlogSubjectCode = true;
   private _validBlogSubjectLibelle = true;

	constructor(private service: BlogSubjectAdminService , @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new BlogSubjectDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validBlogSubjectCode = value;
        this.validBlogSubjectLibelle = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateBlogSubjectCode();
        this.validateBlogSubjectLibelle();
    }

    public validateBlogSubjectCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
        this.errorMessages.push('Code non valide');
        this.validBlogSubjectCode = false;
        } else {
            this.validBlogSubjectCode = true;
        }
    }
    public validateBlogSubjectLibelle(){
        if (this.stringUtilService.isEmpty(this.item.libelle)) {
        this.errorMessages.push('Libelle non valide');
        this.validBlogSubjectLibelle = false;
        } else {
            this.validBlogSubjectLibelle = true;
        }
    }






    get validBlogSubjectCode(): boolean {
        return this._validBlogSubjectCode;
    }

    set validBlogSubjectCode(value: boolean) {
         this._validBlogSubjectCode = value;
    }
    get validBlogSubjectLibelle(): boolean {
        return this._validBlogSubjectLibelle;
    }

    set validBlogSubjectLibelle(value: boolean) {
         this._validBlogSubjectLibelle = value;
    }



    get items(): Array<BlogSubjectDto> {
        return this.service.items;
    }

    set items(value: Array<BlogSubjectDto>) {
        this.service.items = value;
    }

    get item(): BlogSubjectDto {
        return this.service.item;
    }

    set item(value: BlogSubjectDto) {
        this.service.item = value;
    }

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): BlogSubjectCriteria {
        return this.service.criteria;
    }

    set criteria(value: BlogSubjectCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
