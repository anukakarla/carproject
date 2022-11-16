import { TestBed } from '@angular/core/testing';

import { TinterceptorInterceptor } from './tinterceptor.interceptor';

describe('TinterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      TinterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: TinterceptorInterceptor = TestBed.inject(TinterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
