import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-form-error',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './form-error.component.html',
  styleUrl: './form-error.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FormErrorComponent {
  @Input() formError:ValidationErrors | null= {}
 }
