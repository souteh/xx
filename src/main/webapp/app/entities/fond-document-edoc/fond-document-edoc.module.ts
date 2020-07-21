import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { XxSharedModule } from 'app/shared/shared.module';
import { FondDocumentEdocComponent } from './fond-document-edoc.component';
import { FondDocumentEdocDetailComponent } from './fond-document-edoc-detail.component';
import { FondDocumentEdocUpdateComponent } from './fond-document-edoc-update.component';
import { FondDocumentEdocDeleteDialogComponent } from './fond-document-edoc-delete-dialog.component';
import { fondDocumentRoute } from './fond-document-edoc.route';

@NgModule({
  imports: [XxSharedModule, RouterModule.forChild(fondDocumentRoute)],
  declarations: [
    FondDocumentEdocComponent,
    FondDocumentEdocDetailComponent,
    FondDocumentEdocUpdateComponent,
    FondDocumentEdocDeleteDialogComponent,
  ],
  entryComponents: [FondDocumentEdocDeleteDialogComponent],
})
export class XxFondDocumentEdocModule {}
