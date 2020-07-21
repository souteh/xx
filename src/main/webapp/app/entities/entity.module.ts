import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'fond-document-edoc',
        loadChildren: () => import('./fond-document-edoc/fond-document-edoc.module').then(m => m.XxFondDocumentEdocModule),
      },
      {
        path: 'type-de-contenu-edoc',
        loadChildren: () => import('./type-de-contenu-edoc/type-de-contenu-edoc.module').then(m => m.XxTypeDeContenuEdocModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class XxEntityModule {}
