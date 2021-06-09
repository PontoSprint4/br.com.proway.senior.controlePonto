import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';

const routes: Routes = [
  { path: 'turno-index', component: TurnoIndexComponent },
  { path: 'turno-details', component: TurnoDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }