import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { Home2Component } from './components/home2/home2.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { Location } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { UtilisateurListComponent } from './components/utilisateur/utilisateur-list/utilisateur-list.component';
import { UtilisateurCreateComponent } from './components/utilisateur/utilisateur-create/utilisateur-create.component';
import { CommonModule } from '@angular/common';
import { MesTrajetsComponent } from './components/trajet/trajet-list/trajet-list.component';
import { RechercheResultatsComponent } from './components/recherche-cov/recherche-cov.component';
import { MessageListComponent  } from './components/message/message-list/message-list.component';








@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    Home2Component,
    UtilisateurListComponent,
    UtilisateurCreateComponent,
    MesTrajetsComponent,
    RechercheResultatsComponent,
    MessageListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [Location],
  bootstrap: [AppComponent]
})
export class AppModule { }