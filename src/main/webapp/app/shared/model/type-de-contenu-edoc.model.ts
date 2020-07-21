export interface ITypeDeContenuEdoc {
  id?: number;
  denominationAr?: string;
  denominationFr?: string;
  reference?: string;
  code?: string;
  fondDocumentId?: number;
}

export class TypeDeContenuEdoc implements ITypeDeContenuEdoc {
  constructor(
    public id?: number,
    public denominationAr?: string,
    public denominationFr?: string,
    public reference?: string,
    public code?: string,
    public fondDocumentId?: number
  ) {}
}
