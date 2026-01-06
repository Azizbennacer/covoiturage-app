import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { Home2Component } from './components/home2/home2.component';
import { MesTrajetsComponent } from './components/trajet/trajet-list/trajet-list.component';
import { RechercheResultatsComponent } from './components/recherche-cov/recherche-cov.component';
import { MessageListComponent  } from './components/message/message-list/message-list.component';



const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home2', component: Home2Component },
  { path: 'trajet-list', component: MesTrajetsComponent },
  { path: 'recherche-cov', component: RechercheResultatsComponent },
  { path: 'message-list', component: MessageListComponent },
  { path: '**', redirectTo: '/home' }
]

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }