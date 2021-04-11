import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MaganinTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ConsultancyTypeMaganinDeleteDialogComponent } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin-delete-dialog.component';
import { ConsultancyTypeMaganinService } from 'app/entities/consultancy-type-maganin/consultancy-type-maganin.service';

describe('Component Tests', () => {
  describe('ConsultancyTypeMaganin Management Delete Component', () => {
    let comp: ConsultancyTypeMaganinDeleteDialogComponent;
    let fixture: ComponentFixture<ConsultancyTypeMaganinDeleteDialogComponent>;
    let service: ConsultancyTypeMaganinService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MaganinTestModule],
        declarations: [ConsultancyTypeMaganinDeleteDialogComponent],
      })
        .overrideTemplate(ConsultancyTypeMaganinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsultancyTypeMaganinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsultancyTypeMaganinService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
