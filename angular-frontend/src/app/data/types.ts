import {StatusOrder} from "./enums";

export interface EventType {
  id?: number;
  name: string;
  description: string;
  startTime: number;
  endTime?: number | null;
}

export interface HumanType {
  id?: number;
  name: string;
  surname: string;
  birthdayDate: string;
  fate?: string | null;
  time: number;
}

export interface MorType {
  id: number;
  success_percent: number;
  description: string;
}

export interface OrderEventType {
  id?: number;
  orderStatus: StatusOrder;
  event: EventType;
  mors?: MorType[];
}

export interface OrderHumanType {
  id: number;
  orderStatus: StatusOrder;
  human: HumanType;
}


export interface ProductType{
  id: number;
  owner: number;
  name: string;
  description: string;
  timeCurrent: number;
}
export interface ProductOrderType {
  productId: number;
  toUser: string;
  fromUser: number;
  fromTime: number;
  toTime: number;
}
