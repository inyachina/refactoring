import { ChangeDetectionStrategy, Component, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ButtonComponent {
  // TODO надо переделать как в material)
  @Input() public text = '';

  @Input() public disabled = false;

  @Input() public size = '';

  @Input() public appearance = '';

  @Input() public color = '';

  @Input() public noBorders = false;

  @Input() public fullWidth = false;

  @Input() public iconRight = false;

  @Input() public iconLeft = false;

  @Input() public type = 'button';

  @Input() public isSmallPaddingRight = false;
}
