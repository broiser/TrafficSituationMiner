import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'search',
  pure: false
})
export class SearchPipe implements PipeTransform {

  constructor() {}

  transform(value: any, query: string, field: string): any {
    return query ? value.reduce((prev, next) => {
      const queryValue = query.toLowerCase();
      const fieldValue = next[field].toLowerCase();
      if (fieldValue.includes(queryValue)) {
        prev.push(next);
      }
      return prev;
    }, []) : value;
  }
}
