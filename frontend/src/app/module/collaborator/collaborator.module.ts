import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {InputSwitchModule} from 'primeng/inputswitch';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CalendarModule} from 'primeng/calendar';
import {PanelModule} from 'primeng/panel';
import {InputNumberModule} from 'primeng/inputnumber';
import {BadgeModule} from 'primeng/badge';
import { MultiSelectModule } from 'primeng/multiselect';

import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {TabViewModule} from 'primeng/tabview';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessageModule } from 'primeng/message';
import {MessagesModule} from 'primeng/messages';

import { LoginCollaboratorComponent } from './login-collaborator/login-collaborator.component';
import { RegisterCollaboratorComponent } from './register-collaborator/register-collaborator.component';
import { ChangePasswordCollaboratorComponent } from './change-password-collaborator/change-password-collaborator.component';
import { ActivationCollaboratorComponent } from './activation-collaborator/activation-collaborator.component';
import { ForgetPasswordCollaboratorComponent } from './forget-password-collaborator/forget-password-collaborator.component';


import { CloudCollaboratorModule } from './view/cloud/cloud-collaborator.module';
import { CloudCollaboratorRoutingModule } from './view/cloud/cloud-collaborator-routing.module';
import { ForumCollaboratorModule } from './view/forum/forum-collaborator.module';
import { ForumCollaboratorRoutingModule } from './view/forum/forum-collaborator-routing.module';
import { ContactusCollaboratorModule } from './view/contactus/contactus-collaborator.module';
import { ContactusCollaboratorRoutingModule } from './view/contactus/contactus-collaborator-routing.module';
import { PayementCollaboratorModule } from './view/payement/payement-collaborator.module';
import { PayementCollaboratorRoutingModule } from './view/payement/payement-collaborator-routing.module';
import { ProjectCollaboratorModule } from './view/project/project-collaborator.module';
import { ProjectCollaboratorRoutingModule } from './view/project/project-collaborator-routing.module';
import { PackagingCollaboratorModule } from './view/packaging/packaging-collaborator.module';
import { PackagingCollaboratorRoutingModule } from './view/packaging/packaging-collaborator-routing.module';
import { SupportCollaboratorModule } from './view/support/support-collaborator.module';
import { SupportCollaboratorRoutingModule } from './view/support/support-collaborator-routing.module';
import { CollaboratorCollaboratorModule } from './view/collaborator/collaborator-collaborator.module';
import { CollaboratorCollaboratorRoutingModule } from './view/collaborator/collaborator-collaborator-routing.module';
import { BlogCollaboratorModule } from './view/blog/blog-collaborator.module';
import { BlogCollaboratorRoutingModule } from './view/blog/blog-collaborator-routing.module';

import {SecurityModule} from 'src/app/module/security/security.module';
import {SecurityRoutingModule} from 'src/app/module/security/security-routing.module';


@NgModule({
  declarations: [
   LoginCollaboratorComponent,
   RegisterCollaboratorComponent,
   ChangePasswordCollaboratorComponent,
   ActivationCollaboratorComponent,
   ForgetPasswordCollaboratorComponent
  ],
  imports: [
    CommonModule,
    ToastModule,
    ToolbarModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    DropdownModule,
    TabViewModule,
    InputSwitchModule,
    InputTextareaModule,
    CalendarModule,
    PanelModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    BadgeModule,
    MultiSelectModule,
  CloudCollaboratorModule,
  CloudCollaboratorRoutingModule,
  ForumCollaboratorModule,
  ForumCollaboratorRoutingModule,
  ContactusCollaboratorModule,
  ContactusCollaboratorRoutingModule,
  PayementCollaboratorModule,
  PayementCollaboratorRoutingModule,
  ProjectCollaboratorModule,
  ProjectCollaboratorRoutingModule,
  PackagingCollaboratorModule,
  PackagingCollaboratorRoutingModule,
  SupportCollaboratorModule,
  SupportCollaboratorRoutingModule,
  CollaboratorCollaboratorModule,
  CollaboratorCollaboratorRoutingModule,
  BlogCollaboratorModule,
  BlogCollaboratorRoutingModule,
   SecurityModule,
   SecurityRoutingModule
  ],
  exports: [
    LoginCollaboratorComponent,
    RegisterCollaboratorComponent,
    ChangePasswordCollaboratorComponent,
    ActivationCollaboratorComponent,
    ForgetPasswordCollaboratorComponent,

    CloudCollaboratorModule,
    ForumCollaboratorModule,
    ContactusCollaboratorModule,
    PayementCollaboratorModule,
    ProjectCollaboratorModule,
    PackagingCollaboratorModule,
    SupportCollaboratorModule,
    CollaboratorCollaboratorModule,
    BlogCollaboratorModule,
    SecurityModule
  ],

})
export class CollaboratorModule { }
