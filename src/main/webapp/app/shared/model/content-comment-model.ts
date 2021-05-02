export interface IContentComment {
  id?: number;
  title?: string;
  name?: string;
  body?: string;
  city?: string;
}

export class ContentComment implements IContentComment {
  constructor(public id?: number, public title?: string, public name?: string, public body?: string, public city?: string) {}
}
